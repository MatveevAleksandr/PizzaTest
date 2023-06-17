package com.hammersystems.myapplication.pages.main_menu.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.hammersystems.domain.model.MenuItemModel
import com.hammersystems.myapplication.R
import com.squareup.picasso.Picasso

class MenuListRecyclerAdapter(
    private val menuList: List<MenuItemModel>, private val menuItemClick: (MenuItemModel) -> Unit
) : RecyclerView.Adapter<MenuListRecyclerAdapter.MainMenuViewHolder>() {

    class MainMenuViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val menuItemImage: ImageView = item.findViewById(R.id.menu_item_image)
        val menuItemName: TextView = item.findViewById(R.id.menu_item_name)
        val menuItemDescription: TextView = item.findViewById(R.id.menu_item_description)
        val menuItemPrice: TextView = item.findViewById(R.id.menu_item_price)
        val menuItemLayout: ConstraintLayout = item.findViewById(R.id.menu_item_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false)
        return MainMenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainMenuViewHolder, position: Int) {
        Picasso.get().load(menuList[position].imageUrl).fit().into(holder.menuItemImage)
        holder.menuItemName.text = menuList[position].titleName
        holder.menuItemDescription.text = menuList[position].description
        holder.menuItemPrice.text = "от ${menuList[position].price} р"
        holder.menuItemLayout.setOnClickListener {
            menuItemClick(menuList[position])
        }
    }

    override fun getItemCount(): Int {
        return menuList.size
    }
}