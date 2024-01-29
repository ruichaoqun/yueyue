package com.ruichaoqun.yueyue.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ruichaoqun.yueyue.core.model.SystemDataBean
import com.ruichaoqun.yueyue.core.model.SystemDataChildBean
import com.ruichaoqun.yueyue.databinding.FragmentDashboardBinding
import com.ruichaoqun.yueyue.widget.popup.DropBoxPopupWindow
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    val viewModel: DashboardViewModel by viewModels()

    lateinit var firstTagPopup:DropBoxPopupWindow<SystemDataBean>
    lateinit var secondTagPopup:DropBoxPopupWindow<SystemDataChildBean>
    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.apply {

        }

        binding.smartRefresh.apply {

        }

        binding.llLevelOne.setOnClickListener {

        }
        binding.llLevelTwo.setOnClickListener {

        }

        lifecycleScope.launch {
            viewModel.systemTagUiState.collect{
                binding.groupLoading.isVisible = it is SystemTagUiState.Loading
                binding.groupError.isVisible = it is SystemTagUiState.Error
                binding.groupData.isVisible = it is SystemTagUiState.Success
                if (it is SystemTagUiState.Success) {
                    val firstTag = it.systemDatas[0]
                    val secondTag = firstTag.children[0]
                    firstTagPopup = DropBoxPopupWindow(requireContext(),it.systemDatas){position ->
                        viewModel.
                    }
                }
            }
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}