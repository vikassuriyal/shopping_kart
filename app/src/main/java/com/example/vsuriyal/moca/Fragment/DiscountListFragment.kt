package com.example.vsuriyal.moca.Fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.vsuriyal.moca.Adapter.DiscountListAdapter
import com.example.vsuriyal.moca.Beans.BeanClass
import com.example.vsuriyal.moca.R
import com.example.vsuriyal.moca.ViewModel.DiscountListViewModel
import kotlinx.android.synthetic.main.discount_list_fragment.all_discounts_back
import kotlinx.android.synthetic.main.discount_list_fragment.all_discounts_recycler_view

class DiscountListFragment:BaseFragment(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when (p0?.id){
            R.id.all_discounts_back -> removeFragment()
            else -> {}
        }
    }

    val observer = Observer<List<BeanClass.DiscountsBean>>{discountBean ->
        all_discounts_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = discountBean?.let { DiscountListAdapter(context,it) }
        }
    }

    val viewModel by lazy { ViewModelProviders.of(this).get(DiscountListViewModel::class.java) }

    override fun getLayoutView(): Int {
        return R.layout.discount_list_fragment
    }

    override fun onResume() {
        super.onResume()
        all_discounts_back.setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.execute()
        viewModel.getDiscountListLiveData().observe(this, observer)
    }

}