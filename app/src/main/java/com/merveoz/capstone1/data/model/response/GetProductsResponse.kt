package com.merveoz.capstone1.data.model.response

data class GetProductsResponse(
    val products: List<Product>?
) : BaseResponse()
