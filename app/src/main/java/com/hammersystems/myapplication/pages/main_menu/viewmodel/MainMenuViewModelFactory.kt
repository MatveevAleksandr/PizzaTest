package com.hammersystems.myapplication.pages.main_menu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hammersystems.domain.usecases.MenuBannerClickUseCase
import com.hammersystems.domain.usecases.MenuBannerLoadUseCase
import com.hammersystems.domain.usecases.MenuItemClickUseCase
import com.hammersystems.domain.usecases.MenuListLoadUseCase

class MainMenuViewModelFactory(
    private val menuBannerLoadUseCase: MenuBannerLoadUseCase,
    private val menuBannerClickUseCase: MenuBannerClickUseCase,
    private val menuListLoadUseCase: MenuListLoadUseCase,
    private val menuItemClickUseCase: MenuItemClickUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainMenuViewModel(
            menuBannerLoadUseCase = menuBannerLoadUseCase,
            menuBannerClickUseCase = menuBannerClickUseCase,
            menuListLoadUseCase = menuListLoadUseCase,
            menuItemClickUseCase = menuItemClickUseCase
        ) as T
    }
}