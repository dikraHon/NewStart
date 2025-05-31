package com.example.newstart.data.api

import com.example.newstart.data.dto.ProductDto
import com.example.newstart.domain.model.CartItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface FakeStoreApi {
    @GET("products")
    suspend fun getProducts(): List<ProductDto>

    @GET("products/{id}")
    suspend fun getProductDetails(@Path("id") id: Int): ProductDto

    @POST("carts")
    suspend fun addToCart(@Body item: CartItem): Response<Unit>

    @DELETE("carts/{id}")
    suspend fun removeFromCart(@Path("id") id: Int): Response<Unit>

    @PUT("products/{id}")
    suspend fun updateProduct(
        @Path("id") id: Int,
        @Body product: ProductDto
    ): ProductDto

}