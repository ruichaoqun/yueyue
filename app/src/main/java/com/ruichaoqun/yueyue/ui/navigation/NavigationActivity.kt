package com.ruichaoqun.yueyue.ui.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.beloo.widget.chipslayoutmanager.SpacingItemDecoration
import com.ruichaoqun.yueyue.R
import com.ruichaoqun.yueyue.core.model.NavigationBean
import com.ruichaoqun.yueyue.databinding.ActivityNavigationBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NavigationActivity : AppCompatActivity() {

    val viewModel:NavigationViewModel by viewModels()
    private lateinit var binding:ActivityNavigationBinding

    private lateinit var adapter: NavigationAdapter

    private lateinit var leftAdapter:NavigationLeftAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
//            setIcon(R.drawable.ic_back)

            title = getString(R.string.title_navigation)
        }

        binding.recyclerView.apply {
            layoutManager = ChipsLayoutManager.newBuilder(context).build()
            addItemDecoration(
                SpacingItemDecoration(
                    resources.getDimensionPixelOffset(R.dimen.item_space),
                resources.getDimensionPixelOffset(R.dimen.item_space)))
        }
        binding.recyclerViewLeft.apply {
            layoutManager = LinearLayoutManager(context)
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

        leftAdapter = NavigationLeftAdapter(list) {
            scrollToTargetPosition(it)
        }
        binding.recyclerViewLeft.adapter = leftAdapter
        initListener()
    }

    private fun scrollToTargetPosition(position: Int) {
        val target = leftAdapter.mDatas[position]
        for ((index,value) in adapter.mData.withIndex()) {
            if (value is NavigationBean && value.cid == target.cid) {
                binding.recyclerView.scrollToPosition(index)
                break
            }
        }
    }

    private fun initListener() {
        binding.recyclerView.addOnScrollListener(object :OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val manager = binding.recyclerView.layoutManager as ChipsLayoutManager
                val data = adapter.mData[manager.findFirstVisibleItemPosition()]
                if (data is NavigationBean) {
                    selectScrollPosition(data)
                }
            }
        })
    }

    private fun selectScrollPosition(data: NavigationBean) {
        for ((index,value) in leftAdapter.mDatas.withIndex()) {
            if (value == data && index != leftAdapter.mSelectPosition) {
                val manager = binding.recyclerViewLeft.layoutManager as LinearLayoutManager
                if (index < manager.findFirstVisibleItemPosition() || index > manager.findLastVisibleItemPosition()) {
                    binding.recyclerViewLeft.smoothScrollToPosition(index)
                }
                leftAdapter.refreshSelectState(index)
            }
        }
    }
}