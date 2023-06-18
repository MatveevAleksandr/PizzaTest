package com.hammersystems.data.menu.storage.room

import androidx.room.*

@Dao
interface MenuDao {
    @Query("select * from MenuItemEntity")
    fun getAll(): List<MenuItemEntity>

    @Query("select * from MenuItemEntity where category = :category")
    fun getByCategory(category: String): List<MenuItemEntity>

    @Query("select * from MenuItemEntity where id = :id")
    fun getById(id: Int): MenuItemEntity?

    @Update
    fun update(item: MenuItemEntity)

    @Delete
    fun delete(item: MenuItemEntity)

    @Insert
    fun insert(item: MenuItemEntity)
}