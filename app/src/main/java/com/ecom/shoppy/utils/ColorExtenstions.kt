package com.ecom.shoppy.utils

import androidx.compose.ui.graphics.Color
import com.ecom.shoppy.ui.theme.*

internal fun String.getColor() = when(this.lowercase()){
        "black" -> black
        "orange" -> orange
        "yellow","golden" -> yellowColor
        "silver" -> lightSilver
        "blue" -> lightBlue
        "brown" -> brown
        "red" -> red
        "green" -> Color.Green
        else -> Color.Unspecified
    }
