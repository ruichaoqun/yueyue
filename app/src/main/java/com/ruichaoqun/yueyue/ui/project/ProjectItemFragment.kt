package com.ruichaoqun.yueyue.ui.project

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.ruichaoqun.yueyue.core.common.util.dpToPx
import com.ruichaoqun.yueyue.databinding.FragmentProjectItemBinding
import com.ruichaoqun.yueyue.ui.home.CommonFootAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProjectItemFragment :Fragment(){
    val viewModel:ProjectItemViewModel by viewModels()

    lateinit var mBinding:FragmentProjectItemBinding

    @Inject
    lateinit var projectAdapter:ProjectAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentProjectItemBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            viewModel.cid = it.getInt(INTENT_ID)
        }
        mBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                MaterialDividerItemDecoration(context, RecyclerView.VERTICAL).apply {
                    isLastItemDecorated = false
                    dividerThickness = dpToPx(0.8f)
                }
            )
            adapter = projectAdapter.withLoadStateFooter(CommonFootAdapter(projectAdapter::retry))
        }

        mBinding.smartRefresh.setOnRefreshListener {
            refreshData()
        }

        lifecycleScope.launch {
            viewModel.page.collectLatest {
                projectAdapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            projectAdapter.loadStateFlow.collect {
                if (it.refresh !is LoadState.Loading) {
                    mBinding.smartRefresh.finishRefresh()
                }
            }

            projectAdapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { mBinding.recyclerView.scrollToPosition(0) }
        }
    }

    private fun refreshData() {
        lifecycleScope.launch {
            projectAdapter.refresh()
        }
    }


    companion object{
        const val INTENT_ID = "id"

        fun newInstance(id:Int) : ProjectItemFragment{
            return ProjectItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(INTENT_ID,id)
                }
            }
        }
    }
}