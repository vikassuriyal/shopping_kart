package com.example.vsuriyal.moca.Fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.vsuriyal.moca.Adapter.LibraryAdapter
import com.example.vsuriyal.moca.R
import kotlinx.android.synthetic.main.library_fragment.library_recycler_view

class LibraryFragment: BaseFragment() {
    override fun getLayoutView(): Int {
        return R.layout.library_fragment
    }

    override fun onResume() {
        super.onResume()
        val resourceString = resources.getStringArray(R.array.library_array)

        library_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = LibraryAdapter(context,resourceString)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}