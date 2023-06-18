package com.hammersystems.data.menu.storage

import com.hammersystems.data.menu.retrofit.MenuRetrofit
import com.hammersystems.data.menu.retrofit.MenuRetrofitModel
import com.hammersystems.domain.model.MenuItemModel
import com.hammersystems.domain.model.MenuStorageModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

class MenuAPIStorageImpl : MenuStorage {
    override fun loadMenuList(): MenuStorageModel {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build().create<MenuRetrofit>()
        val listCall = retrofit.getMealList().execute()
        return convertAPIModelToStorageAnswerModel(apiCall = listCall)
    }

    override fun loadMenuListByCategory(category: String): MenuStorageModel {
        val allList = loadMenuList()
        val newList = mutableListOf<MenuItemModel>()
        if (allList.isError) return allList
        allList.menuList.forEach {
            if (it.category == category) newList.add(it)
        }
        return MenuStorageModel(
            isError = allList.isError, errorMsg = allList.errorMsg, menuList = newList
        )
    }

    private fun convertAPIModelToStorageAnswerModel(apiCall: Response<MenuRetrofitModel>): MenuStorageModel {
        val isError = apiCall.code() != 200
        val errorMsg = if (isError) apiCall.errorBody().toString() else ""
        val menuList =
            if (isError || apiCall.body() == null) listOf() else apiCall.body()!!.items.map {
                MenuItemModel(
                    titleName = it.mealName,
                    description = "${it.strIngredient1}, ${it.strIngredient2}, ${it.strIngredient3}, ${it.strIngredient4}, ${it.strIngredient5}",
                    imageUrl = it.imageUrl,
                    price = it.mealPrice,
                    category = it.mealCategory
                )
            }.toMutableList()

        return MenuStorageModel(
            isError = isError, errorMsg = errorMsg, menuList = menuList
        )
    }
}