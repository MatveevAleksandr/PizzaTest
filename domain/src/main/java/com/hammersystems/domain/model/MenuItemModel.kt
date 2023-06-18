package com.hammersystems.domain.model

data class MenuItemModel(
    val id: Int,
    val titleName: String,
    val description: String,
    val imageUrl: String,
    val price: Int,
    val category: String
)