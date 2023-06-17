package com.hammersystems.myapplication.pages.main_menu.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hammersystems.domain.model.BannerItemModel
import com.hammersystems.domain.model.MenuItemModel
import com.hammersystems.domain.usecases.MenuBannerClickUseCase
import com.hammersystems.domain.usecases.MenuBannerLoadUseCase
import com.hammersystems.domain.usecases.MenuItemClickUseCase
import com.hammersystems.domain.usecases.MenuListLoadUseCase
import com.hammersystems.myapplication.pages.main_menu.ui.MainMenuStateErrorLoad
import com.hammersystems.myapplication.pages.main_menu.ui.MainMenuStateModel
import com.hammersystems.myapplication.pages.main_menu.ui.MainMenuStateSuccessfulLoad
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainMenuViewModel(
    private val menuBannerLoadUseCase: MenuBannerLoadUseCase,
    private val menuBannerClickUseCase: MenuBannerClickUseCase,
    private val menuListLoadUseCase: MenuListLoadUseCase,
    private val menuItemClickUseCase: MenuItemClickUseCase
) : ViewModel() {
    private val bannerLiveData = MutableLiveData<List<BannerItemModel>?>()
    private val menuListLiveData = MutableLiveData<MainMenuStateModel>()

    fun getBannerLiveData(): MutableLiveData<List<BannerItemModel>?> {
        return bannerLiveData
    }

    fun getMenuListLiveData(): MutableLiveData<MainMenuStateModel> {
        return menuListLiveData
    }

    private fun setBannerLiveData(state: List<BannerItemModel>?) {
        bannerLiveData.value = state
    }

    private fun setMenuListLiveData(state: MainMenuStateModel) {
        menuListLiveData.value = state
    }

    suspend fun initAllBlocks() {
        coroutineScope {
            launch(Dispatchers.IO) {
                initBannerBlock()
                initMenuListBlock()
            }
        }
    }

    private suspend fun initBannerBlock() {
        val result = menuBannerLoadUseCase.execute()
        withContext(Dispatchers.Main) {
            setBannerLiveData(result)
        }
    }

    private suspend fun initMenuListBlock() {
        val result = menuListLoadUseCase.execute()
        val state = if (result.isError) {
            MainMenuStateErrorLoad(errorMsg = result.errorMsg)
        } else {
            MainMenuStateSuccessfulLoad(menuItemList = result.menuList)
        }
        withContext(Dispatchers.Main) {
            setMenuListLiveData(state)
        }
    }

    fun menuBannerClick(item: BannerItemModel) {
        menuBannerClickUseCase.execute(item)
    }

    fun menuItemClick(item: MenuItemModel) {
        menuItemClickUseCase.execute(item)
    }
}