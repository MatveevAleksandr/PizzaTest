package com.hammersystems.myapplication.di

import com.hammersystems.domain.usecases.MenuBannerClickUseCase
import com.hammersystems.domain.usecases.MenuBannerLoadUseCase
import com.hammersystems.domain.usecases.MenuItemClickUseCase
import com.hammersystems.domain.usecases.MenuListLoadUseCase
import com.hammersystems.myapplication.pages.main_menu.viewmodel.MainMenuViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideMainViewModelFactory(
        menuBannerLoadUseCase: MenuBannerLoadUseCase,
        menuBannerClickUseCase: MenuBannerClickUseCase,
        menuListLoadUseCase: MenuListLoadUseCase,
        menuItemClickUseCase: MenuItemClickUseCase
    ): MainMenuViewModelFactory{
        return MainMenuViewModelFactory(
            menuBannerLoadUseCase = menuBannerLoadUseCase,
            menuBannerClickUseCase = menuBannerClickUseCase,
            menuListLoadUseCase = menuListLoadUseCase,
            menuItemClickUseCase = menuItemClickUseCase
        )
    }
}