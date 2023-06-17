package com.hammersystems.myapplication.di

import com.hammersystems.domain.repository.BannerRepository
import com.hammersystems.domain.repository.MenuRepository
import com.hammersystems.domain.usecases.MenuBannerClickUseCase
import com.hammersystems.domain.usecases.MenuBannerLoadUseCase
import com.hammersystems.domain.usecases.MenuItemClickUseCase
import com.hammersystems.domain.usecases.MenuListLoadUseCase
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
}