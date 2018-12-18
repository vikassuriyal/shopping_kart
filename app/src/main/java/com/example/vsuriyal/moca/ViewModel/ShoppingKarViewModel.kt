package com.example.vsuriyal.moca.ViewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.vsuriyal.moca.Beans.BeanClass

class ShoppingKarViewModel:ViewModel() {
    private val list = ArrayList<BeanClass.ShoppingListBean>()
    private val totalPriceLiveData = MutableLiveData<Float>()
    private val totalDiscountLiveData = MutableLiveData<Float>()
    private val totalSubtotalLiveData = MutableLiveData<Int>()
    private val itemListLiveData = MutableLiveData<List<BeanClass.ShoppingListBean>>()

    fun getTotalPriceLiveData(): MutableLiveData<Float> = totalPriceLiveData
    fun getTotalDiscountLiveData(): MutableLiveData<Float> = totalDiscountLiveData
    fun getTotalSubtotalLiveData(): MutableLiveData<Int> = totalSubtotalLiveData
    fun getItemListLiveData(): MutableLiveData<List<BeanClass.ShoppingListBean>> = itemListLiveData


    fun execute(item: BeanClass.ShoppingListBean){
        if(!updateQuantity(item)){
            list.add(item)
        }
        val subtotal = getTotalPrice()
        val totalDiscount = getTotalDiscount()
        val totalPrice = subtotal - totalDiscount
        totalPriceLiveData.value = totalPrice
        totalDiscountLiveData.value = totalDiscount
        totalSubtotalLiveData.value = subtotal
        itemListLiveData.value = list
    }

    fun clearData(){
        list.clear()
        itemListLiveData.value = list
    }

    private fun updateQuantity(item: BeanClass.ShoppingListBean):Boolean{
        for (bean in list){
            if(item.id == bean.id && item.discount == bean.discount){
                bean.quantity+=item.quantity
                return true
            }
        }
        return false
    }

    private fun getTotalPrice():Int{
       var totalPrice = 0
        for (item in list) {
            totalPrice += item.price * item.quantity
       }
        return totalPrice
    }

    private fun getTotalDiscount():Float{
        var totalDiscount = 0f
        for (item in list) {
            totalDiscount += (item.price * item.quantity * item.discount.value) / 100
        }
        return totalDiscount
    }
}