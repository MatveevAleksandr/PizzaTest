package com.hammersystems.myapplication.di

import android.content.Context
import com.hammersystems.myapplication.pages.main_menu.ui.MainFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent {
    fun injectMainMenu(fragment: MainFragment)
    @Component.Factory
    interface AppComponentFactory{
        fun create(@BindsInstance context: Context): AppComponent
    }
}