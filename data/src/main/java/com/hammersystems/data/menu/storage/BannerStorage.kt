package com.hammersystems.data.menu.storage

import com.hammersystems.domain.model.BannerItemModel

interface BannerStorage {
    fun getAll(): List<BannerItemModel>
}