package com.example.vsuriyal.moca.Utils

import android.app.Activity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

object Utils {

    fun addFragment(activity: FragmentActivity?, id:Int, fragment: Fragment, tag: String ) {
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.add(id, fragment)
        fragmentTransaction?.addToBackStack(tag)
        fragmentTransaction?.commit()
    }


    fun getRandomNumber():Int{
        val low = 10
        val high = 100
        val value = java.util.Random().nextInt(high - low) + low
        return value
    }
}