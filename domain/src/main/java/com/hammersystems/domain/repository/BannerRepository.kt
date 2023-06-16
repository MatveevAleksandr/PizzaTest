package com.hammersystems.domain.repository

import com.hammersystems.domain.model.BannerModel

interface BannerRepository {
    fun getAll(): List<BannerModel>
}