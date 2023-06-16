package com.hammersystems.data.menu.storage

import com.hammersystems.data.R
import com.hammersystems.domain.model.BannerModel

class BannerStorageImpl(): BannerStorage {
    override fun getAll(): List<BannerModel>{
        return List(5 ){
            BannerModel(R.drawable.banner_1)
        }
    }
}