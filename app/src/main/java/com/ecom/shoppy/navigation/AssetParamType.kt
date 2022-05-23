package com.ecom.shoppy.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.ecom.shoppy.model.Product
import com.google.gson.Gson

class AssetParamType : NavType<Product>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Product? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Product {
        return Gson().fromJson(value, Product::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Product) {
        bundle.putParcelable(key, value)
    }
}