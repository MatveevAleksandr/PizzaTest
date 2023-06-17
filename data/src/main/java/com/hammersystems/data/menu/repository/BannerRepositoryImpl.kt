package com.hammersystems.data.menu.repository

import com.hammersystems.data.menu.storage.BannerStorage
import com.hammersystems.domain.model.BannerItemModel
import com.hammersystems.domain.repository.BannerRepository

class BannerRepositoryImpl(private val storage: BannerStorage): BannerRepository {
    override fun getAll(): List<BannerItemModel> {
        return storage.getAll()
    }
}