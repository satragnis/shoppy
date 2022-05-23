package com.ecom.shoppy.model

import android.os.Parcelable
import androidx.navigation.Navigator
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    @SerializedName("id"         ) var id         : Int?                = null,
    @SerializedName("categoryId" ) var categoryId : Int?                = null,
    @SerializedName("name"       ) var name       : String?             = null,
    @SerializedName("date_added" ) var dateAdded  : String?             = null,
    @SerializedName("variants"   ) var variants   : ArrayList<Variant>? = null,
    @SerializedName("tax"        ) var tax        : Tax?                = null,
    @SerializedName("price" ) var price : Int?    = null,
):Parcelable,Navigator.Extras
