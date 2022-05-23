package com.ecom.shoppy.utils.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

internal fun <T> MutableLiveData<T>.toLiveData(): LiveData<T> = this