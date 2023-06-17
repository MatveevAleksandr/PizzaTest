package com.hammersystems.data.menu.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface MenuRetrofit {
    @GET("search.php?f=g")
    fun getMealList(): Call<MenuRetrofitModel>
}