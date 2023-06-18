package com.hammersystems.myapplication.pages.main_menu.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hammersystems.data.menu.storage.retrofit.MenuAPIStorageImpl
import com.hammersystems.data.menu.storage.room.MenuRoomStorageImpl
import com.hammersystems.domain.model.BannerItemModel
import com.hammersystems.domain.model.MenuItemModel
import com.hammersystems.domain.model.MenuStorageModel
import com.hammersystems.domain.usecases.*
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
    private val menuItemClickUseCase: MenuItemClickUseCase,
    private val categoryListLoadUseCase: MenuCategoryLoadUseCase,
    private val categoryItemClickUseCase: MenuCategoryClickUseCase,
    private val menuItemRoomStorage: MenuRoomStorageImpl
) : ViewModel() {
    private val bannerLiveData = MutableLiveData<List<BannerItemModel>?>()
    private val menuListLiveData = MutableLiveData<MainMenuStateModel>()
    private val menuCategoryListLiveData = MutableLiveData<List<String>>()

    fun getBannerLiveData(): MutableLiveData<List<BannerItemModel>?> {
        return bannerLiveData
    }

    fun getMenuListLiveData(): MutableLiveData<MainMenuStateModel> {
        return menuListLiveData
    }

    fun getMenuCategoryListLiveData(): MutableLiveData<List<String>> {
        return menuCategoryListLiveData
    }

    private fun setBannerLiveData(state: List<BannerItemModel>?) {
        bannerLiveData.value = state
    }

    private fun setMenuListLiveData(state: MainMenuStateModel) {
        menuListLiveData.value = state
    }

    private fun setMenuCategoryListLiveData(state: List<String>) {
        menuCategoryListLiveData.value = state
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
        val screenState = convertMenuStorageModelToState(result)
        val category = if (result.isError) {
            mutableListOf()
        } else {
            categoryListLoadUseCase.execute(result.menuList)
        }

        if (menuListLoadUseCase.repository.getStorage() is MenuAPIStorageImpl && screenState is MainMenuStateSuccessfulLoad) menuItemRoomStorage.saveMenuStorageItem(
            screenState.menuItemList
        )

        withContext(Dispatchers.Main) {
            setMenuListLiveData(screenState)
            setMenuCategoryListLiveData(category)
        }
    }

    fun menuBannerClick(item: BannerItemModel) {
        menuBannerClickUseCase.execute(item)
    }

    fun menuItemClick(item: MenuItemModel) {
        menuItemClickUseCase.execute(item)
    }

    suspend fun categoryItemClick(category: String) {
        coroutineScope {
            launch(Dispatchers.IO) {
                val result = categoryItemClickUseCase.execute(category)
                withContext(Dispatchers.Main) {
                    setMenuListLiveData(convertMenuStorageModelToState(result))
                }
            }
        }
    }

    private fun convertMenuStorageModelToState(model: MenuStorageModel): MainMenuStateModel {
        return if (model.isError) {
            MainMenuStateErrorLoad(errorMsg = model.errorMsg)
        } else {
            MainMenuStateSuccessfulLoad(menuItemList = model.menuList)
        }
    }
}