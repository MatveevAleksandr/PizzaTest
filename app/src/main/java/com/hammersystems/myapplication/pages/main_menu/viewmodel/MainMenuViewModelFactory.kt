package com.hammersystems.myapplication.pages.main_menu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hammersystems.domain.usecases.*

class MainMenuViewModelFactory(
    private val menuBannerLoadUseCase: MenuBannerLoadUseCase,
    private val menuBannerClickUseCase: MenuBannerClickUseCase,
    private val menuListLoadUseCase: MenuListLoadUseCase,
    private val menuItemClickUseCase: MenuItemClickUseCase,
    private val categoryListLoadUseCase: MenuCategoryLoadUseCase,
    private val categoryItemClickUseCase: MenuCategoryClickUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainMenuViewModel(
            menuBannerLoadUseCase = menuBannerLoadUseCase,
            menuBannerClickUseCase = menuBannerClickUseCase,
            menuListLoadUseCase = menuListLoadUseCase,
            menuItemClickUseCase = menuItemClickUseCase,
            categoryListLoadUseCase = categoryListLoadUseCase,
            categoryItemClickUseCase = categoryItemClickUseCase
        ) as T
    }
}