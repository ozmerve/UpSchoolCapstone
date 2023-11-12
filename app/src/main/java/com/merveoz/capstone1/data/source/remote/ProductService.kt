package com.merveoz.capstone1.data.source.remote

import com.merveoz.capstone1.data.model.request.AddToCartRequest
import com.merveoz.capstone1.data.model.request.ClearCartRequest
import com.merveoz.capstone1.data.model.request.DeleteFromCartRequest
import com.merveoz.capstone1.data.model.response.BaseResponse
import com.merveoz.capstone1.data.model.response.GetCategoriesResponse
import com.merveoz.capstone1.data.model.response.GetProductDetailResponse
import com.merveoz.capstone1.data.model.response.GetProductsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ProductService {

    @GET("get_products.php")
    suspend fun getProducts(): Response<GetProductsResponse>

    @GET("get_product_detail.php")
    suspend fun getProductDetail(
        @Query("id") id: Int
    ): Response<GetProductDetailResponse>

    @GET("get_sale_products.php")
    suspend fun getSaleProducts(): Response<GetProductsResponse>

    @POST("add_to_cart.php")
    suspend fun addToCart(
        @Body request: AddToCartRequest
    ): Response<GetProductsResponse>

    @POST("delete_from_cart.php")
    suspend fun deleteFromCart(
        @Body request: DeleteFromCartRequest
    ): Response<BaseResponse>

    @GET("get_cart_products.php")
    suspend fun getCartProducts(
        @Query("userId") userId: String
    ): Response<GetProductsResponse>

    @POST("clear_cart.php")
    suspend fun clearCart(
        @Body request: ClearCartRequest
    ): Response<BaseResponse>

    @GET("search_product.php")
    suspend fun searchProduct(
        @Query("query") query : String
    ): Response<GetProductsResponse>

    @GET("get_products_by_category.php")
    suspend fun getProductsByCategory(
        @Query("category") category: String
    ): Response<GetProductsResponse>

    @GET("get_categories.php")
    suspend fun getCategories(): Response<GetCategoriesResponse>
}