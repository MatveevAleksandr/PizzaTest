package com.hammersystems.myapplication.pages.main_menu.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.hammersystems.myapplication.R

class MenuCategoryListRecyclerAdapter(
    private val categoryList: List<String>, private val categoryClick: (String) -> Unit
) : RecyclerView.Adapter<MenuCategoryListRecyclerAdapter.MenuCategoryViewHolder>() {
    class MenuCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName: TextView = itemView.findViewById(R.id.category_name)
        val categoryCard: CardView = itemView.findViewById(R.id.category_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuCategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.menu_category_item, parent, false)
        return MenuCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuCategoryViewHolder, position: Int) {
        holder.categoryName.text = categoryList[position]
        holder.categoryCard.setOnClickListener {
            categoryClick(categoryList[position])
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}