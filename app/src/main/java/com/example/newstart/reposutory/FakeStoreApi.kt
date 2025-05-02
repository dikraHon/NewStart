package com.example.newstart.reposutory

import com.example.newstart.data.CartItem
import com.example.newstart.data.CartResponse
import com.example.newstart.data.Product
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface FakeStoreApi {

    // Товары
    @GET("products")
    suspend fun getProducts(): List<Product>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product

    @PUT("products/{id}")
    suspend fun updateProduct(
        @Path("id") id: Int,
        @Body product: Product
    ): Product

    // Корзина
    @POST("carts")
    suspend fun addToCart(@Body cartItem: CartItem): CartResponse

    @DELETE("carts/{id}")
    suspend fun deleteFromCart(@Path("id") id: Int)
}