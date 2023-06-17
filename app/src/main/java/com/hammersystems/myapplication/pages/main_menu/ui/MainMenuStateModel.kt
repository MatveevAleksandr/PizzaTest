package com.hammersystems.myapplication.pages.main_menu.ui

import com.hammersystems.domain.model.MenuItemModel

sealed class MainMenuStateModel
data class MainMenuStateSuccessfulLoad(val menuItemList: List<MenuItemModel>): MainMenuStateModel()
data class MainMenuStateErrorLoad(val errorMsg: String): MainMenuStateModel()