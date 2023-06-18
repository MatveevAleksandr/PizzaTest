package com.hammersystems.myapplication.di

import com.hammersystems.domain.repository.BannerRepository
import com.hammersystems.domain.repository.MenuRepository
import com.hammersystems.domain.usecases.*
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideMenuBannerLoadUseCase(repo: BannerRepository): MenuBannerLoadUseCase {
        return MenuBannerLoadUseCase(repo)
    }

    @Provides
    fun provideMenuBannerClickUseCase(): MenuBannerClickUseCase {
        return MenuBannerClickUseCase()
    }

    @Provides
    fun provideMenuListLoadUseCase(repository: MenuRepository): MenuListLoadUseCase {
        return MenuListLoadUseCase(repository)
    }

    @Provides
    fun provideMenuItemClickUseCase(): MenuItemClickUseCase {
        return MenuItemClickUseCase()
    }

    @Provides
    fun provideMenuCategoryLoadUseCase(): MenuCategoryLoadUseCase {
        return MenuCategoryLoadUseCase()
    }

    @Provides
    fun provideMenuCategoryClickUseCase(repository: MenuRepository): MenuCategoryClickUseCase {
        return MenuCategoryClickUseCase(repository)
    }
}