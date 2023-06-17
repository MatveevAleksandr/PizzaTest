package com.hammersystems.data.menu.storage

import com.hammersystems.domain.model.MenuStorageModel

interface MenuStorage {
    fun loadMenuList(): MenuStorageModel
    fun loadMenuListByCategory(category: String): MenuStorageModel
}