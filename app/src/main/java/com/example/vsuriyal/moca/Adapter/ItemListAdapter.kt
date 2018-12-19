package com.example.vsuriyal.moca.Adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vsuriyal.moca.Beans.BeanClass
import com.example.vsuriyal.moca.Fragment.BaseFragment
import com.example.vsuriyal.moca.MainActivity
import com.example.vsuriyal.moca.R
import com.example.vsuriyal.moca.Utils.Utils.getRandomNumber
import com.example.vsuriyal.moca.Widget.ShoppingDialog
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_adapter_view.view.item_adapter_image
import kotlinx.android.synthetic.main.item_list_adapter_view.view.item_adapter_price
import kotlinx.android.synthetic.main.item_list_adapter_view.view.item_adapter_text

class ItemListAdapter(val context: BaseFragment, var list: List<BeanClass.ItemListBean>) :
    RecyclerView.Adapter<ItemListAdapter.ItemListHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemListHolder {
        val view = LayoutInflater.from(context.context).inflate(R.layout.item_list_adapter_view, p0, false)
        return ItemListHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: ItemListHolder, p1: Int) {
        p0.textView.text = list.get(p1).title
        p0.priceText.text = context.getResources().getString(R.string.price_TEMPLATE, list.get(p1).price.toString())
        Picasso.get().load(list.get(p1).thumbnailUrl).resize(50, 50).centerCrop().into(p0.image)
    }

    fun setData(list: List<BeanClass.ItemListBean>?) {
        this.list = list?: ArrayList()
    }

    fun getData():List<BeanClass.ItemListBean>? {
        return list
    }
    inner class ItemListHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val textView = view.item_adapter_text
        val priceText = view.item_adapter_price
        val image = view.item_adapter_image

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            ShoppingDialog(context , list.get(adapterPosition)).show()
        }
    }
}
