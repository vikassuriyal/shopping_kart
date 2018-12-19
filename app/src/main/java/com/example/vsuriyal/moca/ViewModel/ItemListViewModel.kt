package com.example.vsuriyal.moca.ViewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.example.vsuriyal.moca.Beans.BeanClass
import com.example.vsuriyal.moca.Interface.ApiInterface
import com.example.vsuriyal.moca.Data.ApiClient
import com.example.vsuriyal.moca.Data.DB
import com.example.vsuriyal.moca.Utils.Utils
import retrofit2.Call
import retrofit2.Response


class ItemListViewModel(val context: Application) : AndroidViewModel(context) {

    private val mutableLiveData = MutableLiveData<List<BeanClass.ItemListBean>>()
    fun getItemListLiveData(): MutableLiveData<List<BeanClass.ItemListBean>> = mutableLiveData

    private fun callNetworkForListData(db: DB) {
        val apiService = ApiClient.client.create(ApiInterface::class.java)

        val call = apiService.getItems()
        call.enqueue(object : retrofit2.Callback<List<BeanClass.ItemListBean>> {
            override fun onFailure(call: Call<List<BeanClass.ItemListBean>>?, t: Throwable?) {
            }

            override fun onResponse(
                call: Call<List<BeanClass.ItemListBean>>,
                response: Response<List<BeanClass.ItemListBean>>
            ) {
                val data = response.body()
                val thread = SaveDBValues()
                thread.setThreadValue(data, db, mutableLiveData)
                thread.start()

            }
        })
    }

    fun getItemDataFromSource() {
        val db: DB = DB.getInstance(context)
        var list = db.getAllItems()
        val isDBEmpty = list.isEmpty()
        if (isDBEmpty) {
            callNetworkForListData(db)
        } else {
            mutableLiveData.value = list
        }
    }

    class SaveDBValues : Thread() {

        lateinit var list: List<BeanClass.ItemListBean>
        lateinit var db: DB
        lateinit var mutableLiveData: MutableLiveData<List<BeanClass.ItemListBean>>
        fun setThreadValue(
            values: List<BeanClass.ItemListBean>,
            db: DB,
            liveData: MutableLiveData<List<BeanClass.ItemListBean>>
        ) {
            this.list = values
            this.db = db
            this.mutableLiveData = liveData
        }

        override fun run() {
            super.run()
            if (db.getCount() == 0L) {
                val arr = ArrayList<BeanClass.ItemListBean>()
                for (item in list) {
                    item.price = Utils.getRandomNumber()
                    db.addItem(item)
                    arr.add(item)
                    if (arr.size == 50 || arr.size % 1000 == 0)
                        mutableLiveData.postValue(arr)
                }
                mutableLiveData.postValue(arr)
            }
        }

    }
}