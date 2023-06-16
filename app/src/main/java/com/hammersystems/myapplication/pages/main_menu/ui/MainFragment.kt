package com.hammersystems.myapplication.pages.main_menu.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hammersystems.myapplication.R
import com.hammersystems.myapplication.app.App
import com.hammersystems.myapplication.pages.main_menu.adapters.BannerRecyclerAdapter
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



        bannerRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        viewModel.getBannerLiveData().observe(this) {
            when (it.isNullOrEmpty()) {
                true -> {
                    bannerRecyclerView.adapter = null
                }
                false -> {
                    bannerRecyclerView.adapter = BannerRecyclerAdapter(bannerList = it,
                        bannerClick = { viewModel.menuBannerClick() })
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.initAllBlocks()
        }
    }
}