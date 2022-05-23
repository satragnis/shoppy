package com.ecom.shoppy.app

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.ecom.shoppy.utils.extensions.toLiveData
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShoppyApp:Application() {
}