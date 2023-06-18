package com.hammersystems.myapplication.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.room.Room
import com.hammersystems.data.menu.repository.BannerRepositoryImpl
import com.hammersystems.data.menu.repository.MenuRepositoryImpl
import com.hammersystems.data.menu.storage.BannerStorage
import com.hammersystems.data.menu.storage.BannerStorageImpl
import com.hammersystems.domain.storage.MenuStorage
import com.hammersystems.data.menu.storage.retrofit.MenuAPIStorageImpl
import com.hammersystems.data.menu.storage.room.MenuDao
import com.hammersystems.data.menu.storage.room.MenuRoomStorageImpl
import com.hammersystems.domain.repository.BannerRepository
import com.hammersystems.domain.repository.MenuRepository
import com.hammersystems.myapplication.AppDataBase
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
    fun provideMenuStorage(context: Context, menuDao: MenuDao): MenuStorage {
        return if (isOnline(context)) MenuAPIStorageImpl()
        else MenuRoomStorageImpl(menuDao)
    }

    @Provides
    @Singleton
    fun provideAppDataBase(context: Context): AppDataBase {
        return Room.databaseBuilder(
            context, AppDataBase::class.java, "food_market"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMenuDao(dataBase: AppDataBase): MenuDao {
        return dataBase.menuDao()
    }

    @Provides
    @Singleton
    fun provideMenuRoomStorageImpl(dao: MenuDao): MenuRoomStorageImpl {
        return MenuRoomStorageImpl(dao)
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return true
            }
        }
        return false
    }
}