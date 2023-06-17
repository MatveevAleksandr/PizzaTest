package com.hammersystems.myapplication.di

import com.hammersystems.data.menu.repository.BannerRepositoryImpl
import com.hammersystems.data.menu.repository.MenuRepositoryImpl
import com.hammersystems.data.menu.storage.BannerStorage
import com.hammersystems.data.menu.storage.BannerStorageImpl
import com.hammersystems.data.menu.storage.MenuAPIStorageImpl
import com.hammersystems.data.menu.storage.MenuStorage
import com.hammersystems.domain.repository.BannerRepository
import com.hammersystems.domain.repository.MenuRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideBannerStorage(): BannerStorage {
        return BannerStorageImpl()
    }

    @Provides
    @Singleton
    fun provideBannerRepository(storage: BannerStorage): BannerRepository {
        return BannerRepositoryImpl(storage)
    }

    @Provides
    @Singleton
    fun provideMenuRepository(storage: MenuStorage): MenuRepository {
        return MenuRepositoryImpl(storage)
    }

    @Provides
    @Singleton
    fun provideMenuStorage(): MenuStorage {
        return MenuAPIStorageImpl()
    }
}