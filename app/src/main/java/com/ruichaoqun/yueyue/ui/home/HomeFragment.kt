package com.ruichaoqun.yueyue.ui.home

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
import com.ruichaoqun.yueyue.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Objects
import javax.inject.Inject
import com.ruichaoqun.yueyue.core.common.util.dpToPx
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter

@AndroidEntryPoint
class HomeFragment : Fragment() {

    val viewMode:HomeViewModel by viewModels()

    @Inject
    lateinit var homeAdapter: HomeAdapter

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
            viewMode.pageFlow.collect {
                homeAdapter.submitData(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            homeAdapter.loadStateFlow.distinctUntilChanged().collect {   loadState ->
                Log.e("AAAAA",loadState.toString())
                binding.swipeRefresh.setOnRefreshing(loadState.refresh is LoadState.Loading)

            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            homeAdapter.loadStateFlow
                // Use a state-machine to track LoadStates such that we only transition to
                // NotLoading from a RemoteMediator load if it was also presented to UI.
                // Only emit when REFRESH changes, as we only want to react on loads replacing the
                // list.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                // Scroll to top is synchronous with UI updates, even if remote load was triggered.
                .collect { binding.recyclerView.scrollToPosition(0) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}