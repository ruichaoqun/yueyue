package com.ruichaoqun.yueyue.ui.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ruichaoqun.yueyue.databinding.FragmentProjectItemBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectItemFragment :Fragment(){
    lateinit var mBinding:FragmentProjectItemBinding

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

    }


    companion object{
        const val INTENT_ID = "id"

        fun createProjectItemFragment(id:Int) : ProjectItemFragment{
            return ProjectItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(INTENT_ID,id)
                }
            }
        }
    }
}