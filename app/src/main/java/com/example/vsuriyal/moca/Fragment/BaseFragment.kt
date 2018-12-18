package com.example.vsuriyal.moca.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vsuriyal.moca.Beans.BeanClass
import com.example.vsuriyal.moca.MainActivity

abstract class BaseFragment:Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutView(),container,false)
    }

    abstract fun getLayoutView():Int

    fun showProgress(){
        (activity as MainActivity).getProgressBar().visibility = View.VISIBLE
    }

    fun hideProgress(){
        (activity as MainActivity).getProgressBar().visibility = View.GONE
    }

    fun removeFragment(){
        val count = activity?.supportFragmentManager?.getBackStackEntryCount()?:0
        if(count > 0) {
            hideProgress()
            activity?.getSupportFragmentManager()?.popBackStack()
        }
    }

    fun addToShoppingKart(bean:BeanClass.ShoppingListBean) {
        (activity as MainActivity) .getShoppingKart().addItem(bean)
    }


}