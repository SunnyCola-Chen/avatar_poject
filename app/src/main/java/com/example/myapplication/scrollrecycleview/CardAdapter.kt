package com.example.myapplication.scrollrecycleview

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R

// 适配器
class CardAdapter(
    private val items: List<CardItem>,
    private val onItemClick: (Int, CardItem) -> Unit
) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    private var centerPosition = 0

    fun setCenterPosition(position: Int) {
        val oldPosition = centerPosition
        centerPosition = position
        notifyItemChanged(oldPosition)
        notifyItemChanged(centerPosition)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, position == centerPosition)
        holder.itemView.setOnClickListener { onItemClick(position, item) }
    }

    override fun getItemCount() = items.size

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.card_image)
        private val title: TextView = itemView.findViewById(R.id.card_title)
        private val overlay: View = itemView.findViewById(R.id.overlay)

        fun bind(item: CardItem, isCenter: Boolean) {
            title.text = item.title

            // 使用Glide加载图片
            Glide.with(itemView)
                .load(item.imageUrl)
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(image)

            // 根据是否在中心位置应用不同样式
            if (isCenter) {
                title.setTextSize(20f)
                title.setTypeface(title.typeface, Typeface.BOLD)
                title.alpha = 1f
                overlay.alpha = 0.2f
            } else {
                title.setTextSize(16f)
                title.typeface = Typeface.DEFAULT
                title.alpha = 0.8f
                overlay.alpha = 0.4f
            }
        }
    }
}