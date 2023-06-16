package com.hammersystems.myapplication.di

import com.hammersystems.domain.repository.BannerRepository
import com.hammersystems.domain.usecases.MenuBannerClickUseCase
import com.hammersystems.domain.usecases.MenuBannerLoadUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideMenuBannerLoadUseCase(repo: BannerRepository): MenuBannerLoadUseCase{
        return MenuBannerLoadUseCase(repo)
    }

    @Provides
    fun provideMenuBannerClickUseCase(): MenuBannerClickUseCase{
        return MenuBannerClickUseCase()
    }
}