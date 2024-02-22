package com.ruichaoqun.yueyue.ui.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ruichaoqun.yueyue.databinding.FragmentProjectBinding
import dagger.hilt.android.AndroidEntryPoint
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
    }
}