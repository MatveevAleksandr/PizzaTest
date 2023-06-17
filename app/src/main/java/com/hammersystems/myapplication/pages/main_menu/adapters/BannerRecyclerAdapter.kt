package com.hammersystems.myapplication.pages.main_menu.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.hammersystems.domain.model.BannerItemModel
import com.hammersystems.myapplication.R

class BannerRecyclerAdapter(
    private val bannerList: List<BannerItemModel>, private val bannerClick: (BannerItemModel) -> Unit
) : RecyclerView.Adapter<BannerRecyclerAdapter.BannerViewHolder>() {
    class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bannerImage: ImageView = itemView.findViewById(R.id.banner_image)
        val bannerCard: CardView = itemView.findViewById(R.id.banner_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val viewItem =
            LayoutInflater.from(parent.context).inflate(R.layout.banner_item, parent, false)
        return BannerViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bannerImage.setImageResource(bannerList[position].imageRes)
        holder.bannerCard.setOnClickListener {
            bannerClick(bannerList[position])
        }
    }

    override fun getItemCount(): Int {
        return bannerList.size
    }
}