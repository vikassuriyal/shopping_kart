package com.example.vsuriyal.moca.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vsuriyal.moca.Beans.BeanClass
import com.example.vsuriyal.moca.R
import kotlinx.android.synthetic.main.discounts_list_adapter_view.view.discount_adapter_key
import kotlinx.android.synthetic.main.discounts_list_adapter_view.view.discount_adapter_value


class DiscountListAdapter(val context: Context, val list: List<BeanClass.DiscountsBean>) :
        RecyclerView.Adapter<DiscountListAdapter.DiscountListHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): DiscountListHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.discounts_list_adapter_view, p0, false)
        return DiscountListHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: DiscountListHolder, p1: Int) {
        p0.discountKey.text = list.get(p1).discountsKey
        p0.discountValue.text = list.get(p1).discountsValue
    }

    class DiscountListHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val discountKey = view.discount_adapter_key
        val discountValue = view.discount_adapter_value

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {

        }
    }
}
