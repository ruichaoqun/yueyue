package com.ruichaoqun.yueyue.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.ruichaoqun.yueyue.core.common.util.dpToPx
import com.ruichaoqun.yueyue.core.model.SystemDataBean
import com.ruichaoqun.yueyue.core.model.SystemDataChildBean
import com.ruichaoqun.yueyue.databinding.FragmentDashboardBinding
import com.ruichaoqun.yueyue.ui.home.CommonFootAdapter
import com.ruichaoqun.yueyue.ui.home.setOnRefreshing
import com.ruichaoqun.yueyue.widget.popup.DropBoxPopupWindow
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SystemFragment : Fragment() {

    val viewModel: DashboardViewModel by viewModels()

    lateinit var firstTagPopup: DropBoxPopupWindow<SystemDataBean>
    lateinit var secondTagPopup: DropBoxPopupWindow<SystemDataChildBean>
    private var _binding: FragmentDashboardBinding? = null

    @Inject
    lateinit var systemDataAdapter: SystemDataAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                MaterialDividerItemDecoration(context, RecyclerView.VERTICAL).apply {
                    isLastItemDecorated = false
                    dividerThickness = dpToPx(0.8f)
                }
            )
            adapter =
                systemDataAdapter.withLoadStateFooter(CommonFootAdapter(systemDataAdapter::retry))
        }

        binding.llLevelOne.setOnClickListener {
            firstTagPopup.showAsDropDown(binding.llLevelOne)
        }
        binding.llLevelTwo.setOnClickListener {
            secondTagPopup.showAsDropDown(binding.llLevelTwo)
        }

        binding.smartRefresh.setOnRefreshListener {
            refreshData()
        }

        lifecycleScope.launch {
            viewModel.systemTagUiState.collect {
                binding.groupLoading.isVisible = it is SystemTagUiState.Loading
                binding.groupError.isVisible = it is SystemTagUiState.Error
                binding.groupData.isVisible = it is SystemTagUiState.Success
                if (it is SystemTagUiState.Success) {
                    val firstTag = it.systemDatas[0]
                    binding.tvLevelOne.text = firstTag.name
                    binding.tvLevelTwo.text = firstTag.children[0].name
                    firstTagPopup =
                        DropBoxPopupWindow(requireContext(), it.systemDatas) { position ->
                            val data = firstTagPopup.mData[position]
                            binding.tvLevelOne.text = data.name
                            binding.tvLevelTwo.text = data.children[0].name
                            viewModel.cid = data.children[0].id
                            refreshData()
                            secondTagPopup.apply {
                                refreshData(data.children)
                                showAsDropDown(binding.llLevelTwo)
                            }
                        }
                    secondTagPopup =
                        DropBoxPopupWindow(requireContext(), firstTag.children) { position ->
                            val data = secondTagPopup.mData[position]
                            viewModel.cid = data.id
                            refreshData()
                        }
                    getArticles()
                }
            }
        }

        lifecycleScope.launch {
            systemDataAdapter.loadStateFlow.collect {
                if (it.refresh !is LoadState.Loading) {
                    binding.smartRefresh.finishRefresh()
                }
            }

            systemDataAdapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.recyclerView.scrollToPosition(0) }
        }
    }

    private fun refreshData() {
        lifecycleScope.launch {
            systemDataAdapter.refresh()
        }
    }

    private fun getArticles() {
        lifecycleScope.launch {
            viewModel.page.collectLatest {
                systemDataAdapter.submitData(it)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}