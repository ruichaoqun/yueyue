package com.ruichaoqun.yueyue.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayoutMediator
import com.ruichaoqun.yueyue.core.common.base.CommonPagerAdapter
import com.ruichaoqun.yueyue.core.model.PublicNo
import com.ruichaoqun.yueyue.databinding.FragmentPublicNoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PublicNoFragment : Fragment() {

    val viewModel:PublicNoViewModel by viewModels()

    private var _binding: FragmentPublicNoBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPublicNoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserval()
    }

    private fun initObserval() {
        lifecycleScope.launch {
            viewModel.publicNoUiState.collect{
                binding.groupLoading.isVisible = it is PublicNoUiState.Loading
                binding.groupError.isVisible = it is PublicNoUiState.Error
                binding.groupData.isVisible = it is PublicNoUiState.Success
                if (it is PublicNoUiState.Success) {
                    initTab(it.list)
                }
            }
        }
    }

    private fun initTab(projects: List<PublicNo>) {
        binding.viewPage.adapter = CommonPagerAdapter(requireActivity(),projects.size){
            PublicNoItemFragment.newInstance(projects[it].id)
        }
        binding.viewPage.offscreenPageLimit = projects.size

        TabLayoutMediator(binding.tabLayout,binding.viewPage) { tab, position ->
            tab.text = projects[position].name
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}