package com.hammersystems.domain.model

data class MenuStorageModel(
    val isError: Boolean, val errorMsg: String, val menuList: List<MenuItemModel>
)