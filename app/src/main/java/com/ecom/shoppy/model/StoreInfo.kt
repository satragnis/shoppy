package com.ecom.shoppy.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoreInfo(
    @SerializedName("title"      ) var title      : String?               = null,
    @SerializedName("contactUs"  ) var contactUs  : ContactUs?            = null,
    @SerializedName("categories" ) var categories : ArrayList<Category>? = null
): Parcelable
