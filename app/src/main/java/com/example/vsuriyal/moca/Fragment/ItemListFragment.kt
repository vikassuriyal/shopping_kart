package com.example.vsuriyal.moca.Fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.vsuriyal.moca.Adapter.ItemListAdapter
import com.example.vsuriyal.moca.Adapter.LibraryAdapter
import com.example.vsuriyal.moca.Beans.BeanClass
import com.example.vsuriyal.moca.Data.DB
import com.example.vsuriyal.moca.R
import com.example.vsuriyal.moca.ViewModel.ItemListViewModel
import kotlinx.android.synthetic.main.item_list_fragment.*

class ItemListFragment : BaseFragment(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.item_list_back -> {
                removeFragment()
            }
            else -> {
            }
        }
    }

    val viewModel by lazy {
        ViewModelProviders.of(this).get(ItemListViewModel::class.java)
    }

    val observer = Observer<List<BeanClass.ItemListBean>> { itemList ->
        val currentCount = item_list_recycler_view.adapter?.itemCount ?: 0
        if (item_list_recycler_view.adapter == null || currentCount == 0 ) {
            item_list_recycler_view.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = itemList?.let { ItemListAdapter(this@ItemListFragment, itemList) }
            }
        }
        hideProgress()
    }

    override fun getLayoutView(): Int {
        return R.layout.item_list_fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getItemListLiveData().observe(this, observer)
        showProgress()
        viewModel.getItemDataFromSource()
    }

    override fun onResume() {
        super.onResume()
        item_list_back.setOnClickListener(this)
    }
}