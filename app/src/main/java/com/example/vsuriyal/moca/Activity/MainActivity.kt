package com.example.vsuriyal.moca

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.ProgressBar
import com.example.vsuriyal.moca.Fragment.LibraryFragment
import com.example.vsuriyal.moca.Fragment.ShoppingKartFragment
import com.example.vsuriyal.moca.Interface.ShoppingKart
import com.example.vsuriyal.moca.Widget.ShoppingDialog
import kotlinx.android.synthetic.main.activity_main.progress

class MainActivity : AppCompatActivity() {
    private val progressView by lazy { progress }
    private val libraryFragment by lazy { LibraryFragment() }
    private val shoppingKartFragment by lazy { ShoppingKartFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        addFragment(R.id.discount_list, libraryFragment, "Library")
        addFragment(R.id.shopping_list, shoppingKartFragment, "ShoppingList")
    }

    fun addFragment(id:Int, fragment:Fragment, text: String ) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction?.add(id, fragment, text)
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction.commit()
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
