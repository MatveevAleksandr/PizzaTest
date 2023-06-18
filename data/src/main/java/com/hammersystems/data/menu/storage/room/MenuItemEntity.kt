package com.hammersystems.data.menu.storage.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MenuItemEntity(
    @PrimaryKey val id: Int,
    val titleName: String,
    val description: String,
    val imageUrl: String,
    val price: Int,
    val category: String
)