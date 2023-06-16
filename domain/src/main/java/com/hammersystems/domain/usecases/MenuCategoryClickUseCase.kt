package com.hammersystems.domain.usecases

import com.hammersystems.domain.model.MenuItemModel
import com.hammersystems.domain.repository.MenuRepository

class MenuCategoryClickUseCase(private val repository: MenuRepository) {
    fun execute(category: String): List<MenuItemModel>{
        return repository.loadMenuListByCategory(category)
    }
}