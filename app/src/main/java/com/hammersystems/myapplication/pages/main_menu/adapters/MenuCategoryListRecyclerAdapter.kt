package com.hammersystems.myapplication.pages.main_menu.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.hammersystems.myapplication.R

class MenuCategoryListRecyclerAdapter(
    private val categoryList: List<String>, private val categoryClick: (String) -> Unit
) : RecyclerView.Adapter<MenuCategoryListRecyclerAdapter.MenuCategoryViewHolder>() {

    private val menuCategoryCardList = mutableListOf<MenuCategoryCardView>()

    class MenuCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName: TextView = itemView.findViewById(R.id.category_name)
        val categoryCard: MaterialCardView = itemView.findViewById(R.id.category_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuCategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.menu_category_item, parent, false)
        return MenuCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuCategoryViewHolder, position: Int) {
        val menuCategoryCardItem =
            MenuCategoryCardView(card = holder.categoryCard, txt = holder.categoryName)
        menuCategoryCardList.add(menuCategoryCardItem)
        holder.categoryName.text = categoryList[position]
        holder.categoryCard.setOnClickListener {
            focusOnItem(menuCategoryCardItem)
            categoryClick(categoryList[position])
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    private fun focusOnItem(cardItem: MenuCategoryCardView) {
        cardItem.card.setCardBackgroundColor("#33FD3A69".toColorInt())
        cardItem.txt.setTextColor("#FD3A69".toColorInt())
        menuCategoryCardList.forEach {
            if (it != cardItem) {
                it.card.setCardBackgroundColor("#FFFFFF".toColorInt())
                it.txt.setTextColor("#C3C4C9".toColorInt())
            }
        }
    }
}

data class MenuCategoryCardView(val card: MaterialCardView, val txt: TextView)