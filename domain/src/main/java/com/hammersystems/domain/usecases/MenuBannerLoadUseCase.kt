package com.hammersystems.domain.usecases

import com.hammersystems.domain.model.BannerModel
import com.hammersystems.domain.repository.BannerRepository

class MenuBannerLoadUseCase(private val repository: BannerRepository) {
    fun execute(): List<BannerModel>{
        return repository.getAll()
    }
}