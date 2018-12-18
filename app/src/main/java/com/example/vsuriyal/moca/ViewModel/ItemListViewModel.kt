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
import retrofit2.Call
import retrofit2.Response


class ItemListViewModel(val context: Application) : AndroidViewModel(context) {
    private val mutableLiveData = MutableLiveData<List<BeanClass.ItemListBean>>()
    fun getItemListLiveData(): MutableLiveData<List<BeanClass.ItemListBean>> = mutableLiveData

    private fun callNetworkForListData(db: DB, thread: SaveDBValues) {
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
                thread.setThreadValue(data,db)
                thread.start()
                mutableLiveData.value = data
            }
        })
    }

    fun getItemDataFromSource() {
        val thread = SaveDBValues()
        val db:DB = DB.getInstance(context)
        callNetworkForListData(db, thread)

    }

    private class SaveDBValues() : Thread() {
        lateinit var list: List<BeanClass.ItemListBean>
        lateinit var db: DB

        fun setThreadValue(values: List<BeanClass.ItemListBean>, db: DB) {
            this.list = values
            this.db = db
        }

        override fun run() {
            super.run()
            db.deleteAllRecords()
            db.addItemList(list)
        }

    }
}