package com.hammersystems.domain.repository

import com.hammersystems.domain.model.BannerItemModel

interface BannerRepository {
    fun getAll(): List<BannerItemModel>
}