package com.hammersystems.data.menu.storage.room

import com.hammersystems.domain.storage.MenuStorage
import com.hammersystems.domain.model.MenuItemModel
import com.hammersystems.domain.model.MenuStorageModel

class MenuRoomStorageImpl(private val dao: MenuDao) : MenuStorage {
    override fun loadMenuList(): MenuStorageModel {
        return MenuStorageModel(
            isError = false,
            errorMsg = "",
            menuList = dao.getAll().map { convertEntityToMenuItemModel(it) }.toMutableList()
        )
    }

    override fun loadMenuListByCategory(category: String): MenuStorageModel {
        return MenuStorageModel(isError = false,
            errorMsg = "",
            menuList = dao.getByCategory(category).map { convertEntityToMenuItemModel(it) }
                .toMutableList())
    }

    fun saveMenuStorageItem(menuStorageModel: List<MenuItemModel>) {
        menuStorageModel.forEach {
            if (dao.getById(it.id) == null) dao.insert(
                convertMenuItemModelToEntity(
                    it
                )
            )
            else dao.update(
                convertMenuItemModelToEntity(
                    it
                )
            )
        }
    }

    private fun convertEntityToMenuItemModel(entity: MenuItemEntity): MenuItemModel {
        return MenuItemModel(
            id = entity.id,
            titleName = entity.titleName,
            description = entity.description,
            imageUrl = entity.imageUrl,
            price = entity.price,
            category = entity.category
        )
    }

    private fun convertMenuItemModelToEntity(item: MenuItemModel): MenuItemEntity {
        return MenuItemEntity(
            id = item.id,
            titleName = item.titleName,
            description = item.description,
            imageUrl = item.imageUrl,
            price = item.price,
            category = item.category
        )
    }
}