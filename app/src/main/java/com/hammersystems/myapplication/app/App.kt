package com.hammersystems.myapplication.app

import android.app.Application
import com.hammersystems.myapplication.di.AppComponent
import com.hammersystems.myapplication.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}