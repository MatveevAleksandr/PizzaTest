package com.hammersystems.domain.repository

import com.hammersystems.domain.model.MenuItemModel

interface MenuRepository {
    fun loadMenuList(): List<MenuItemModel>
    fun loadMenuListByCategory(category: String): List<MenuItemModel>
}