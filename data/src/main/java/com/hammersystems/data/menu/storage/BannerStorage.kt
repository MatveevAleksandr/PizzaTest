package com.hammersystems.data.menu.storage

import com.hammersystems.domain.model.BannerModel

interface BannerStorage {
    fun getAll(): List<BannerModel>
}