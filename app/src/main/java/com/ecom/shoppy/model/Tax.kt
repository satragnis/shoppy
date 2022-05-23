package com.ecom.shoppy.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tax(
    @SerializedName("name"  ) var name  : String? = null,
    @SerializedName("value" ) var value : Double? = null
):Parcelable
