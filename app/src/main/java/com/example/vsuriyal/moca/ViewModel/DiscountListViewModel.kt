package com.example.vsuriyal.moca.ViewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.vsuriyal.moca.Beans.BeanClass

class DiscountListViewModel:ViewModel() {
    private val mutableLiveData = MutableLiveData<List<BeanClass.DiscountsBean>>()

    fun getDiscountListLiveData():MutableLiveData<List<BeanClass.DiscountsBean>> = mutableLiveData

    fun execute(){
        val list  = ArrayList<BeanClass.DiscountsBean>()
        list.add(BeanClass.DiscountsBean("Discount A","0 %"))
        list.add(BeanClass.DiscountsBean("Discount B","10 %"))
        list.add(BeanClass.DiscountsBean("Discount C","35.5 %"))
        list.add(BeanClass.DiscountsBean("Discount D","50 %"))
        list.add(BeanClass.DiscountsBean("Discount E","100 %"))
        mutableLiveData.value = list
    }
}