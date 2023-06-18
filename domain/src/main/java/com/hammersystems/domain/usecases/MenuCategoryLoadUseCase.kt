package com.hammersystems.domain.usecases

import com.hammersystems.domain.model.MenuItemModel

class MenuCategoryLoadUseCase {
    fun execute(menuList: List<MenuItemModel>): List<String> {
        val result = mutableSetOf<String>()
        menuList.forEach {
            result.add(it.category)
        }
        return result.toList()
    }
}