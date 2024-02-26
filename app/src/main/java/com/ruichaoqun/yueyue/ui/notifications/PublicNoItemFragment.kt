package com.ruichaoqun.yueyue.ui.notifications

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.ruichaoqun.yueyue.core.common.util.dpToPx
import com.ruichaoqun.yueyue.databinding.FragmentPublicNoItemBinding
import com.ruichaoqun.yueyue.ui.home.CommonFootAdapter
import com.ruichaoqun.yueyue.ui.project.ProjectItemFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PublicNoItemFragment : Fragment() {

    val viewModel:PublicNoItemViewModel by viewModels()

    lateinit var mBinding:FragmentPublicNoItemBinding

    @Inject
    lateinit var publicNoAdapter: PublicNoItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mBinding = FragmentPublicNoItemBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            viewModel.cid = it.getInt(ProjectItemFragment.INTENT_ID)
        }
        mBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                MaterialDividerItemDecoration(context, RecyclerView.VERTICAL).apply {
                    isLastItemDecorated = false
                    dividerThickness = dpToPx(0.8f)
                }
            )
            adapter = publicNoAdapter.withLoadStateFooter(CommonFootAdapter(publicNoAdapter::retry))
        }

        mBinding.smartRefresh.setOnRefreshListener {
            refreshData()
        }

        lifecycleScope.launch {
            viewModel.page.collectLatest {
                publicNoAdapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            publicNoAdapter.loadStateFlow.collect {
                if (it.refresh !is LoadState.Loading) {
                    mBinding.smartRefresh.finishRefresh()
                }
            }

            publicNoAdapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { mBinding.recyclerView.scrollToPosition(0) }
        }
    }

    private fun refreshData() {
        lifecycleScope.launch {
            publicNoAdapter.refresh()
        }
    }

    companion object {
        const val INTENT_ID = "id"

        @JvmStatic
        fun newInstance(id: Int) =
            PublicNoItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(INTENT_ID, id)
                }
            }
    }
}