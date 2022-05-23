package com.ecom.shoppy.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderDoneList(
    @SerializedName("orderDone") var orderDone: ArrayList<OrderDone>? = null,
    @SerializedName("totalAmount") var totalAmount: Double? = null,
    @SerializedName("totalQuantity") var totalQuantity: Int? = null,
    @SerializedName("modeOfPayment") var modeOfPayment: String? = null,
    @SerializedName("transactionId") var transactionId: String? = null,
    @SerializedName("orderId") var orderId: String? = null,
) : Parcelable
