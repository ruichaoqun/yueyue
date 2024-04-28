package com.ruichaoqun.yueyue.ui.mine

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ruichaoqun.yueyue.R
import com.ruichaoqun.yueyue.databinding.FragmentMineBinding
import com.ruichaoqun.yueyue.ui.login.LoginActivity

class MineFragment : Fragment() {

    companion object {
        fun newInstance() = MineFragment()
    }

    val viewModel: MineViewModel by viewModels()
    private lateinit var mBinding:FragmentMineBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mBinding = FragmentMineBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.ivUser.setOnClickListener {
            navigationLogin()
        }
    }

    private fun navigationLogin() {
        startActivity(Intent(requireActivity(),LoginActivity::class.java))
    }

}