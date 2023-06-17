package com.hammersystems.domain.usecases

import com.hammersystems.domain.model.BannerItemModel
import com.hammersystems.domain.repository.BannerRepository

class MenuBannerLoadUseCase(private val repository: BannerRepository) {
    fun execute(): List<BannerItemModel>{
        return repository.getAll()
    }
}