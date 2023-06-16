package com.hammersystems.myapplication.pages.main_menu.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hammersystems.domain.model.BannerModel
import com.hammersystems.domain.usecases.MenuBannerClickUseCase
import com.hammersystems.domain.usecases.MenuBannerLoadUseCase

class MainMenuViewModel(
    private val menuBannerLoadUseCase: MenuBannerLoadUseCase,
    private val menuBannerClickUseCase: MenuBannerClickUseCase
): ViewModel() {
    private val bannerLiveData = MutableLiveData<List<BannerModel>?>()

    fun getBannerLiveData(): MutableLiveData<List<BannerModel>?>{
        return bannerLiveData
    }

    private fun setBannerLiveData(state: List<BannerModel>?){
        bannerLiveData.value = state
    }

    fun initAllBlocks(){
        initBannerBlock()
    }

    private fun initBannerBlock(){
        setBannerLiveData(menuBannerLoadUseCase.execute())
    }

    fun menuBannerClick(){
        menuBannerClickUseCase.execute()
    }
}