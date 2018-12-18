package com.example.vsuriyal.moca.Fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.opengl.Visibility
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.vsuriyal.moca.Adapter.ShoppingKartAdapter
import com.example.vsuriyal.moca.Beans.BeanClass
import com.example.vsuriyal.moca.Interface.ShoppingKart
import com.example.vsuriyal.moca.R
import com.example.vsuriyal.moca.Utils.Utils
import com.example.vsuriyal.moca.ViewModel.ShoppingKarViewModel
import kotlinx.android.synthetic.main.shopping_adapter_view.shopping_list_price
import kotlinx.android.synthetic.main.shopping_kart_fragment.shopping_kart_discount
import kotlinx.android.synthetic.main.shopping_kart_fragment.shopping_kart_discount_text
import kotlinx.android.synthetic.main.shopping_kart_fragment.shopping_kart_subtotal
import kotlinx.android.synthetic.main.shopping_kart_fragment.shopping_kart_subtotal_text
import kotlinx.android.synthetic.main.shopping_kart_fragment.shopping_list_charge
import kotlinx.android.synthetic.main.shopping_kart_fragment.shopping_list_clear
import kotlinx.android.synthetic.main.shopping_kart_fragment.shopping_list_recycler_view
import java.text.DecimalFormat

class ShoppingKartFragment:BaseFragment(), ShoppingKart {

    val viewModel by lazy {
        ViewModelProviders.of(this).get(ShoppingKarViewModel::class.java)
    }

    override fun addItem(item: BeanClass.ShoppingListBean) {
        viewModel.execute(item)
    }

    override fun getLayoutView(): Int {
        return R.layout.shopping_kart_fragment
    }

    val itemDataObserver = Observer<List<BeanClass.ShoppingListBean>>{
        if(it?.size == 0){
         makeWidgetVisibilityGone()
        }else {
            makeWidgetVisible()
            val recyclerView = shopping_list_recycler_view
            if (recyclerView.adapter != null) {
                recyclerView.adapter?.notifyDataSetChanged()
            } else {
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = it?.let { ShoppingKartAdapter(context, it) }
                }
            }
        }
    }

    val totalPriceObserver = Observer<Float>{
        shopping_list_charge.text = context?.resources?.getString(R.string.price_TEMPLATE,  DecimalFormat("##.##").format(it))
    }

    val totalDiscountObserver = Observer<Float>{
        shopping_kart_discount.text = context?.resources?.getString(R.string.price_TEMPLATE, DecimalFormat("##.##").format(it))
    }

    val totalSubtotalObserver = Observer<Int>{
        shopping_kart_subtotal.text = context?.resources?.getString(R.string.price_TEMPLATE, DecimalFormat("##.##").format(it))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getItemListLiveData().observe(this,itemDataObserver)
        viewModel.getTotalPriceLiveData().observe(this,totalPriceObserver)
        viewModel.getTotalDiscountLiveData().observe(this,totalDiscountObserver)
        viewModel.getTotalSubtotalLiveData().observe(this,totalSubtotalObserver)
    }

    override fun onResume() {
        super.onResume()
        shopping_list_clear.setOnClickListener{
            viewModel.clearData()
        }
    }

    fun makeWidgetVisibilityGone(){
        shopping_list_recycler_view.visibility = View.GONE
        shopping_kart_subtotal.visibility = View.GONE
        shopping_kart_subtotal_text.visibility = View.GONE
        shopping_kart_discount.visibility = View.GONE
        shopping_kart_discount_text.visibility = View.GONE
        shopping_list_clear.visibility = View.GONE
        shopping_list_charge.visibility = View.GONE
    }

    fun makeWidgetVisible(){
        shopping_list_recycler_view.visibility = View.VISIBLE
        shopping_kart_subtotal.visibility = View.VISIBLE
        shopping_kart_subtotal_text.visibility = View.VISIBLE
        shopping_kart_discount.visibility = View.VISIBLE
        shopping_kart_discount_text.visibility = View.VISIBLE
        shopping_list_clear.visibility = View.VISIBLE
        shopping_list_charge.visibility = View.VISIBLE
    }

    companion object {
        fun getInstance():ShoppingKartFragment{
            return ShoppingKartFragment()
        }
    }

}