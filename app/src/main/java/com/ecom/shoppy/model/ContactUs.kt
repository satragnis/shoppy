package com.ecom.shoppy.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactUs(
    @SerializedName("address" ) var address : String? = null,
    @SerializedName("email"   ) var email   : String? = null,
    @SerializedName("phone"   ) var phone   : String? = null,
):Parcelable
