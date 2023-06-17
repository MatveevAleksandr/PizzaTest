package com.hammersystems.data.menu.retrofit

import com.google.gson.annotations.SerializedName

data class MenuRetrofitModel(
    @SerializedName("meals") val items: List<MenuItemRetrofitModel>
)

data class MenuItemRetrofitModel(
    @SerializedName("strMealThumb") val imageUrl: String,
    @SerializedName("strMeal") val mealName: String,
    @SerializedName("strIngredient1") val strIngredient1: String = "",
    @SerializedName("strIngredient2") val strIngredient2: String = "",
    @SerializedName("strIngredient3") val strIngredient3: String = "",
    @SerializedName("strIngredient4") val strIngredient4: String = "",
    @SerializedName("strIngredient5") val strIngredient5: String = "",
    @SerializedName("strCategory") val mealCategory: String,
//    прайса нет, пусть его заменит айдишник
    @SerializedName("idMeal") val mealPrice: Int
)