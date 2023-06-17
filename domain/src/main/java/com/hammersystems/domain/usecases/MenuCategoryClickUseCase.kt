package com.hammersystems.domain.usecases

import com.hammersystems.domain.model.MenuStorageModel
import com.hammersystems.domain.repository.MenuRepository

class MenuCategoryClickUseCase(private val repository: MenuRepository) {
    fun execute(category: String): MenuStorageModel {
        return repository.loadMenuListByCategory(category)
    }
}