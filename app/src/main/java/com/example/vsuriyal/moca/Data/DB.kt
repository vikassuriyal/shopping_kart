package com.example.vsuriyal.moca.Data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.vsuriyal.moca.Beans.BeanClass
import com.example.vsuriyal.moca.Utils.Utils.getRandomNumber

class DB private constructor(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_NAME = "ItemDatabase"
        private val DATABASE_VERSION = 1
        private val TABLE_NAME = "itemlist"
        private val ALBUM_ID = "albumId"
        private val ID = "id"
        private val TITLE = "title"
        private val THUMBNAIL_URL = "thumbnailUrl"
        private val URL = "url"
        private val PRICE = "price"

        private var instance:DB? = null

        @Synchronized
        fun getInstance(context:Context):DB {
            instance = instance ?: DB(context)
            return instance as DB
        }
    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        val sql = "CREATE TABLE " + TABLE_NAME + " (\n" +
                ALBUM_ID + " INTEGER NOT NULL,\n" +
                ID + " INTEGER NOT NULL,\n" +
                TITLE + " varchar(200) NOT NULL,\n" +
                THUMBNAIL_URL + " varchar(200) NOT NULL,\n" +
                URL + " varchar(200) NOT NULL,\n" +
                PRICE + " INTEGER NOT NULL\n" +
                ");"

        sqLiteDatabase.execSQL(sql)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        onCreate(sqLiteDatabase)
    }


    fun deleteAllRecords(){
        val db = this.writableDatabase
        db.execSQL("delete from "+ TABLE_NAME)
    }
    private fun getAllData():Cursor{
        val db = getReadableDatabase()
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    fun getCount():Long{
        val db = getReadableDatabase()
       return DatabaseUtils.queryNumEntries(db, TABLE_NAME);
    }

    private fun addItem(albumID: Int, id: Int, title: String, url: String, thumbnailUrl: String, price: Int) {
        val contentValues = ContentValues()
        contentValues.put(ALBUM_ID, albumID)
        contentValues.put(ID, id)
        contentValues.put(TITLE, title)
        contentValues.put(URL, url)
        contentValues.put(THUMBNAIL_URL, thumbnailUrl)
        contentValues.put(PRICE, price)
        val db = writableDatabase
        db.insert(TABLE_NAME, null, contentValues)
    }

    fun addItem(bean: BeanClass.ItemListBean) {
        addItem(bean.albumId,bean.id,bean.title, bean.url, bean.thumbnailUrl, bean.price)
    }

    fun addItemList(list: List<BeanClass.ItemListBean>) {
        for (bean in list) {
            addItem(bean)
        }
    }

    @Synchronized fun getAllItems():List<BeanClass.ItemListBean> {
        val list = ArrayList<BeanClass.ItemListBean>()
        val cursor = getAllData()

        if (cursor.moveToFirst()) {
            do {
                list.add(BeanClass.ItemListBean(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5)
                ))
            } while (cursor.moveToNext())
        }
        return list
    }
}