package com.merveoz.capstone1.data.model.response

data class GetCategoriesResponse(
    val categories: List<Category>? = null
):BaseResponse()
