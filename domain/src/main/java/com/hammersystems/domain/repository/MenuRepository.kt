package com.hammersystems.domain.repository

import com.hammersystems.domain.model.MenuStorageModel
import com.hammersystems.domain.storage.MenuStorage

interface MenuRepository {
    fun loadMenuList(): MenuStorageModel
    fun loadMenuListByCategory(category: String): MenuStorageModel
    fun getStorage(): MenuStorage
}