package com.hammersystems.data.menu.repository

import com.hammersystems.data.menu.storage.MenuStorage
import com.hammersystems.domain.model.MenuStorageModel
import com.hammersystems.domain.repository.MenuRepository

class MenuRepositoryImpl(private val storage: MenuStorage) : MenuRepository {
    override fun loadMenuList(): MenuStorageModel {
        return storage.loadMenuList()
    }

    override fun loadMenuListByCategory(category: String): MenuStorageModel {
        return storage.loadMenuListByCategory(category)
    }
}