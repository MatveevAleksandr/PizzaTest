package com.hammersystems.myapplication.di

import com.hammersystems.data.menu.storage.room.MenuRoomStorageImpl
import com.hammersystems.domain.usecases.*
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
        menuItemClickUseCase: MenuItemClickUseCase,
        categoryListLoadUseCase: MenuCategoryLoadUseCase,
        categoryItemClickUseCase: MenuCategoryClickUseCase,
        menuItemRoomStorage: MenuRoomStorageImpl
    ): MainMenuViewModelFactory {
        return MainMenuViewModelFactory(
            menuBannerLoadUseCase = menuBannerLoadUseCase,
            menuBannerClickUseCase = menuBannerClickUseCase,
            menuListLoadUseCase = menuListLoadUseCase,
            menuItemClickUseCase = menuItemClickUseCase,
            categoryListLoadUseCase = categoryListLoadUseCase,
            categoryItemClickUseCase = categoryItemClickUseCase,
            menuItemRoomStorage = menuItemRoomStorage
        )
    }
}