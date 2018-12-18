package com.example.vsuriyal.moca.Adapter

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vsuriyal.moca.Fragment.DiscountListFragment
import com.example.vsuriyal.moca.Fragment.ItemListFragment
import com.example.vsuriyal.moca.R
import com.example.vsuriyal.moca.Utils.Utils
import kotlinx.android.synthetic.main.library_adapter_view.view.library_adapter_text

class LibraryAdapter(val context: Context, val list: Array<String>) :
    RecyclerView.Adapter<LibraryAdapter.LibraryHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): LibraryHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.library_adapter_view, p0, false)
        return LibraryHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: LibraryHolder, p1: Int) {
        p0.bind(list.get(p1))
    }

    inner class LibraryHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        val textView = view.library_adapter_text

        init {
            view.setOnClickListener(this)
        }

        fun bind(text: String) {
            textView.text = text
        }

        override fun onClick(p0: View?) {
            if (adapterPosition == 0) {
                Utils.addFragment(context as FragmentActivity, R.id.discount_list, DiscountListFragment(), "DiscountListFragment")
            } else if (adapterPosition == 1) {
                Utils.addFragment(context as FragmentActivity,R.id.discount_list, ItemListFragment(), "DiscountListFragment")
            }
        }

    }
}
