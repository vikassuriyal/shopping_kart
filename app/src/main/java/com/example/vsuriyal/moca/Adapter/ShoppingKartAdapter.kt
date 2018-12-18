package com.example.vsuriyal.moca.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vsuriyal.moca.Beans.BeanClass
import com.example.vsuriyal.moca.R

import kotlinx.android.synthetic.main.shopping_adapter_view.view.shopping_list_name
import kotlinx.android.synthetic.main.shopping_adapter_view.view.shopping_list_price
import kotlinx.android.synthetic.main.shopping_adapter_view.view.shopping_list_quantity

class ShoppingKartAdapter(val context: Context, val list: List<BeanClass.ShoppingListBean>) :
        RecyclerView.Adapter<ShoppingKartAdapter.ShoppingKartListHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ShoppingKartListHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.shopping_adapter_view, p0, false)
        return ShoppingKartListHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: ShoppingKartListHolder, p1: Int) {
        p0.itemPrice.text = context.getResources().getString(R.string.price_TEMPLATE, list.get(p1).price.toString())
        p0.itemQuantity.text = context.getResources().getString(R.string.quantity_TEMPLATE, list.get(p1).quantity.toString())
        p0.itemTitle.text = list.get(p1).title
    }

    class ShoppingKartListHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemTitle = view.shopping_list_name
        val itemQuantity = view.shopping_list_quantity
        val itemPrice = view.shopping_list_price
    }
}
