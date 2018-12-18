package com.example.vsuriyal.moca.Beans

import com.example.vsuriyal.moca.Utils.DiscountEnum

class BeanClass {
    data class ItemListBean(val albumId:Int, val id:Int, val title:String, val url:String, val thumbnailUrl:String, var price:Int)
    data class ShoppingListBean(val id:Int, val title:String, val price:Int, var quantity:Int, val discount:DiscountEnum)
    data class DiscountsBean(val discountsKey:String, val discountsValue:String )
}