package com.hammersystems.data.menu.storage

import com.hammersystems.domain.model.MenuItemModel

interface MenuStorage {
    fun loadMenuList(): List<MenuItemModel>
    fun loadMenuListByCategory(category: String): List<MenuItemModel>
}