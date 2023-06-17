package com.hammersystems.data.menu.storage

import com.hammersystems.data.R
import com.hammersystems.domain.model.BannerItemModel

class BannerStorageImpl(): BannerStorage {
    override fun getAll(): List<BannerItemModel>{
        return List(5 ){
            BannerItemModel(R.drawable.banner)
        }
    }
}