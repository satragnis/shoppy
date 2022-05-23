package com.ecom.shoppy.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderDone(
    var productId     : Int?    = null,
    var categotyId    : Int?    = null,
    var name          : String? = null,
    var variantId     : Int?    = null,
    var color         : String? = null,
    var size          : Int?    = null,
    var price         : Int?    = null,
    var quantity      : Int?    = null,
    var taxPercent    : Double? = null,
    var isOrderDone   : Boolean = false,
):Parcelable
