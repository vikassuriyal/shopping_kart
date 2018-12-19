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
    override fun getFragmentTag(): String {
        return "ItemListFragment"
    }

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
        activity?.let { ViewModelProviders.of(it).get(ItemListViewModel::class.java) }
    }

    val observer = Observer<List<BeanClass.ItemListBean>> { itemList ->
        val recycler = item_list_recycler_view
        if (recycler.adapter == null) {
            recycler.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = itemList?.let { ItemListAdapter(this@ItemListFragment, itemList) }
            }
        } else {
            val adapter = recycler.adapter as ItemListAdapter
            adapter.apply {
                setData(itemList)
                notifyDataSetChanged()

            }
        }
        hideProgress()
    }

    override fun getLayoutView(): Int {
        return R.layout.item_list_fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel?.getItemListLiveData()?.observe(this, observer)
        showProgress()
        viewModel?.getItemDataFromSource()
    }

    override fun onResume() {
        super.onResume()
        item_list_back.setOnClickListener(this)
    }

    companion object {
        fun getInstance(): ItemListFragment {
            return ItemListFragment()
        }
    }
}