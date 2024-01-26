package com.ruichaoqun.yueyue.ui.home

import android.os.Bundle
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
import com.ruichaoqun.yueyue.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.ruichaoqun.yueyue.core.common.util.dpToPx
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter

@AndroidEntryPoint
class HomeFragment : Fragment() {

    val viewMode:HomeViewModel by viewModels()

    @Inject
    lateinit var homeAdapter: HomeAdapter

    @Inject
    lateinit var bannerAdapter:HomeBannerAdapter

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.banner.setAdapter(bannerAdapter)
        binding.recyclerView.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeAdapter.withLoadStateFooter(CommonFootAdapter(homeAdapter::retry))

            addItemDecoration(
                MaterialDividerItemDecoration(context, RecyclerView.VERTICAL).apply {
                    isLastItemDecorated = false
                    dividerThickness = dpToPx(0.8f)
                },
            )
        }

        binding.swipeRefresh.setOnRefreshListener {
            homeAdapter.refresh()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewMode.homeUiState.collect{
                when(it) {
                    is HomeBannerUiState.Success -> {
                        bannerAdapter.setDatas(it.banners)
                    }
                    is HomeBannerUiState.Error -> {

                    }
                    is HomeBannerUiState.Loading -> {

                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewMode.pageFlow.collect{
                homeAdapter.submitData(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            homeAdapter.loadStateFlow.collect {
                binding.swipeRefresh.setOnRefreshing(it.refresh is LoadState.Loading)
            }

            homeAdapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.recyclerView.scrollToPosition(0) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}