package com.hammersystems.data.menu.repository

import com.hammersystems.data.menu.storage.BannerStorage
import com.hammersystems.domain.model.BannerModel
import com.hammersystems.domain.repository.BannerRepository

class BannerRepositoryImpl(private val storage: BannerStorage): BannerRepository {
    override fun getAll(): List<BannerModel> {
        return storage.getAll()
    }
}