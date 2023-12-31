package com.hammersystems.domain.usecases

import com.hammersystems.domain.model.MenuStorageModel
import com.hammersystems.domain.repository.MenuRepository

class MenuListLoadUseCase(val repository: MenuRepository) {
    fun execute(): MenuStorageModel {
        return repository.loadMenuList()
    }
}