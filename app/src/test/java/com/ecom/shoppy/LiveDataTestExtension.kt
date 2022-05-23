@file:Suppress("UNCHECKED_CAST")

package com.ecom.shoppy

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
    val observer = OneTimeObserver(handler = onChangeHandler)
    observe(observer, observer)
}

fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnitTest: TimeUnit = TimeUnit.SECONDS,
    afterObserve: ()-> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T>{
        override fun onChanged(t: T) {
            data = t
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }

    }
    this.observeForever(observer)
    try {
        afterObserve.invoke()
        if(!latch.await(time,timeUnitTest)){
            throw TimeoutException("Live Data Value was never set.")
        }
    } finally {
        this.removeObserver(observer)
    }
    return data as T
}