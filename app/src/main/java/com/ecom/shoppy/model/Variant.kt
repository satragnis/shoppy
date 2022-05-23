package com.ecom.shoppy.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Variant(
    @SerializedName("id"    ) var id    : Int?    = null,
    @SerializedName("color" ) var color : String? = null,
    @SerializedName("size"  ) var size  : Int?    = null,
) :Parcelable