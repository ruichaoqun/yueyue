package com.ruichaoqun.yueyue.ui.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.beloo.widget.chipslayoutmanager.SpacingItemDecoration
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.ruichaoqun.yueyue.R
import com.ruichaoqun.yueyue.core.common.util.dpToPx
import com.ruichaoqun.yueyue.core.model.NavigationBean
import com.ruichaoqun.yueyue.databinding.ActivityNavigationBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NavigationActivity : AppCompatActivity() {

    val viewModel:NavigationViewModel by viewModels()
    private lateinit var binding:ActivityNavigationBinding

    private lateinit var adapter: NavigationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.apply {
            layoutManager = ChipsLayoutManager.newBuilder(context).build()
            addItemDecoration(
                SpacingItemDecoration(
                    resources.getDimensionPixelOffset(R.dimen.item_space),
                resources.getDimensionPixelOffset(R.dimen.item_space)))
        }

        lifecycleScope.launch {
            viewModel.navigationUiState.collect{
                when(it) {
                    NavigationUiState.Error -> 1
                    NavigationUiState.Loading -> 2
                    is NavigationUiState.Success -> initData(it.list)
                }
            }
        }
    }

    private fun initData(list: List<NavigationBean>) {
        var datas:MutableList<Any> = mutableListOf()
        for (item in list) {
            datas.add(item)
            for (child in item.articles) {
                datas.add(child)
            }
        }
        adapter = NavigationAdapter(datas)
        binding.recyclerView.adapter = adapter
    }
}