package com.hammersystems.domain.repository

import com.hammersystems.domain.model.MenuStorageModel

interface MenuRepository {
    fun loadMenuList(): MenuStorageModel
    fun loadMenuListByCategory(category: String): MenuStorageModel
}