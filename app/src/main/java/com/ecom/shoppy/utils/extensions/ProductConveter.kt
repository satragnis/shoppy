package com.ecom.shoppy.utils.extensions

import com.ecom.shoppy.model.OrderDone
import com.ecom.shoppy.model.Product

internal fun Product.toOrderDoneType(quantity: Int, variantIndex: Int): OrderDone = OrderDone(
    productId = id,
    categotyId = categoryId,
    quantity = quantity,
    name = name,
    variantId = variants?.get(variantIndex)?.id,
    color = variants?.get(variantIndex)?.color,
    price = price,
    taxPercent = tax?.value,
    size = variants?.get(variantIndex)?.size,
)
