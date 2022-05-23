package com.ecom.shoppy.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductsDataList(
    @SerializedName("products" ) var products : ArrayList<Product> = arrayListOf()
): Parcelable
