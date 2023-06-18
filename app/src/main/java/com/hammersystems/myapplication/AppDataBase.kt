package com.hammersystems.myapplication

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hammersystems.data.menu.storage.room.MenuDao
import com.hammersystems.data.menu.storage.room.MenuItemEntity

@Database(entities = [MenuItemEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun menuDao(): MenuDao
}