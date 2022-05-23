package com.ecom.shoppy.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.ecom.shoppy.app.ShoppyApp
import com.ecom.shoppy.model.OrderDoneList
import com.ecom.shoppy.model.ProductsDataList
import com.ecom.shoppy.model.StoreInfo
import com.google.gson.Gson

object FakeApi {
    var appContext:Context? = null

    fun getProductList(): ProductsDataList? {
        var string: String? = null
        val assetManager = appContext?.assets
            string = assetManager?.open("Products.json")?.bufferedReader().use { it?.readText() }
        return Gson().fromJson(string, ProductsDataList::class.java)

    }

    fun getStoreInfo(): StoreInfo? {
        val assetManager = appContext?.assets
        val string = assetManager?.open("StoreInfo.json")?.bufferedReader().use {
            it?.readText()
        }
        return Gson().fromJson(string, StoreInfo::class.java)
    }


    fun getOrderDoneList(): OrderDoneList? {
        val assetManager = appContext?.assets
        val string = assetManager?.open("OrderDone.json")?.bufferedReader().use {
            it?.readText()
        }
        return Gson().fromJson(string, OrderDoneList::class.java)
    }

    fun postOrderDone(orderDoneList: OrderDoneList) {
        Log.d(">>>>>", "postOrderDone: $orderDoneList")
    }
}