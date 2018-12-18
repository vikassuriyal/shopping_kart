package com.example.vsuriyal.moca

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.ProgressBar
import com.example.vsuriyal.moca.Fragment.LibraryFragment
import com.example.vsuriyal.moca.Fragment.ShoppingKartFragment
import com.example.vsuriyal.moca.Interface.ShoppingKart
import com.example.vsuriyal.moca.Utils.Utils
import com.example.vsuriyal.moca.Utils.Utils.addFragment
import com.example.vsuriyal.moca.Widget.ShoppingDialog
import kotlinx.android.synthetic.main.activity_main.progress

class MainActivity : AppCompatActivity() {
    private val progressView by lazy { progress }
    private val libraryFragment by lazy { LibraryFragment.getInstance() }
    private val shoppingKartFragment by lazy { ShoppingKartFragment.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        Utils.addFragment(this, R.id.discount_list, libraryFragment, "Library")
        Utils.addFragment(this, R.id.shopping_list, shoppingKartFragment, "ShoppingList")
    }

    override fun onBackPressed() {
        finish()
    }

    fun getShoppingKart():ShoppingKart{
        return shoppingKartFragment
    }

    fun getProgressBar():ProgressBar{
        return progressView
    }

}
