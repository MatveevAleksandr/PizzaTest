package com.hammersystems.myapplication.pages.main_menu.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hammersystems.domain.usecases.MenuListLoadUseCase
import com.hammersystems.myapplication.R
import com.hammersystems.myapplication.app.App
import com.hammersystems.myapplication.pages.main_menu.adapters.BannerRecyclerAdapter
import com.hammersystems.myapplication.pages.main_menu.adapters.MenuListRecyclerAdapter
import com.hammersystems.myapplication.pages.main_menu.viewmodel.MainMenuViewModel
import com.hammersystems.myapplication.pages.main_menu.viewmodel.MainMenuViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: MainMenuViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_menu_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.applicationContext as App).appComponent.injectMainMenu(this)
        val viewModel = ViewModelProvider(this, viewModelFactory)[MainMenuViewModel::class.java]
        val bannerRecyclerView: RecyclerView = view.findViewById(R.id.main_banner_recycler)
        val menuRecyclerView: RecyclerView = view.findViewById(R.id.main_menu_recycler)


        bannerRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        viewModel.getBannerLiveData().observe(this) { bannerItemList ->
            when (bannerItemList.isNullOrEmpty()) {
                true -> {
                    bannerRecyclerView.adapter = null
                }
                false -> {
                    bannerRecyclerView.adapter = BannerRecyclerAdapter(bannerList = bannerItemList,
                        bannerClick = { viewModel.menuBannerClick(it) })
                }
            }
        }


        menuRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewModel.getMenuListLiveData().observe(this) { state ->
            when (state) {
                is MainMenuStateSuccessfulLoad -> {
                    menuRecyclerView.adapter =
                        MenuListRecyclerAdapter(menuList = state.menuItemList, menuItemClick = {
                            viewLifecycleOwner.lifecycleScope.launch {
                                viewModel.menuItemClick(it)
                            }
                        })
                }
                is MainMenuStateErrorLoad -> {
                    Toast.makeText(
                        activity, "Ошибка загрузки меню, ${state.errorMsg}", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.initAllBlocks()
        }
    }
}