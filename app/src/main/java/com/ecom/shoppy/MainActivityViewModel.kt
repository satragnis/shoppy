package com.ecom.shoppy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ecom.shoppy.model.*
import com.ecom.shoppy.utils.FakeApi
import com.ecom.shoppy.utils.extensions.toLiveData
import com.ecom.shoppy.utils.extensions.toOrderDoneType

class MainActivityViewModel : ViewModel() {

    private val _productsDataListLiveData = MutableLiveData<ProductsDataList?>()
    val productsDataListLiveData = _productsDataListLiveData.toLiveData()

    private val _storeInfoLiveData = MutableLiveData<StoreInfo?>()
    val storeInfoLiveData = _storeInfoLiveData.toLiveData()

    private val _progressBarLiveData = MutableLiveData<Boolean?>(null)
    val progressBarLiveData = _progressBarLiveData.toLiveData()

    private val _orderPlacedLiveData = MutableLiveData<Boolean?>(null)
    val orderPlacedLiveData = _orderPlacedLiveData.toLiveData()

    private val _itemAddedToCartLiveData = MutableLiveData<Boolean?>(null)
    val itemAddedToCartLiveData = _itemAddedToCartLiveData.toLiveData()

    var hashMapOrderDone = HashMap<Int,OrderDone>()


    fun getProductList(){
        _progressBarLiveData.value = true
        _productsDataListLiveData.value = FakeApi.getProductList()
        _progressBarLiveData.value = false

    }

    fun getStoreInfo(){
        _progressBarLiveData.value = true
        _storeInfoLiveData.value = FakeApi.getStoreInfo()
        _progressBarLiveData.value = false
    }

    fun addProductToCart(productId: Int, quantity: Int, variantIndex: Int, product: Product):Boolean{
        // add product to list of OrderDoneList
        if(quantity>0) {
            val orderDoneItem =
                product.toOrderDoneType(quantity = quantity, variantIndex = variantIndex)
            hashMapOrderDone[productId] = orderDoneItem
            return if (hashMapOrderDone.containsKey(productId)) {
                _itemAddedToCartLiveData.value = true
                true
            } else {
                _itemAddedToCartLiveData.value = false
                false
            }
        }
        _itemAddedToCartLiveData.value = false
        return false
    }

    fun placeOrder(orderDoneList:OrderDoneList):Boolean{
        return if(hashMapOrderDone.size>0 && (orderDoneList.totalAmount ?: 0.0) > 0.0 ) {
            val result = orderDoneList.copy(
                orderDone = ArrayList(hashMapOrderDone.values)
            )
            FakeApi.postOrderDone(orderDoneList = result)
            _orderPlacedLiveData.value = true
            hashMapOrderDone.clear()
            true
        } else{
            _orderPlacedLiveData.value = false
            false
        }

    }
}