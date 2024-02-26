package com.ruichaoqun.yueyue.ui.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ruichaoqun.yueyue.core.common.base.CommonPagerAdapter
import com.ruichaoqun.yueyue.core.model.ProjectItemBean
import com.ruichaoqun.yueyue.databinding.FragmentProjectBinding
import com.ruichaoqun.yueyue.ui.dashboard.SystemTagUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProjectFragment:Fragment() {

    val viewModel:ProjectViewModel by viewModels()

    private var _binding: FragmentProjectBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var adapter:ProjectAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserval()
    }

    private fun initObserval() {
        lifecycleScope.launch {
            viewModel.projectUiState.collect{
                binding.groupLoading.isVisible = it is ProjectUiState.Loading
                binding.groupError.isVisible = it is ProjectUiState.Error
                binding.groupData.isVisible = it is ProjectUiState.Success
                if (it is ProjectUiState.Success) {
                    initTab(it.projects)
                }
            }
        }
    }

    private fun initTab(projects: List<ProjectItemBean>) {
        binding.viewPage.adapter = CommonPagerAdapter(requireActivity(),projects.size){
            ProjectItemFragment.newInstance(projects[it].id)
        }
        binding.viewPage.offscreenPageLimit = projects.size

        TabLayoutMediator(binding.tabLayout,binding.viewPage) { tab, position ->
            tab.text = projects[position].name
        }.attach()
    }
}