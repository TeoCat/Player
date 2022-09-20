package com.example.player.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.player.R
import com.example.player.models.Channel
import kotlinx.android.synthetic.main.item_channel.view.*

class ChannelsAdapter : RecyclerView.Adapter<ChannelsAdapter.ChannelsViewHolder>() {

    class ChannelsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Channel>() {
        override fun areItemsTheSame(oldItem: Channel, newItem: Channel): Boolean {
            return oldItem.address == newItem.address
        }

        override fun areContentsTheSame(oldItem: Channel, newItem: Channel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChannelsViewHolder {
        return ChannelsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_channel, parent, false
            )
        )
    }

    private var onBtnClickListener: ((Channel) -> Unit)? = null

    override fun onBindViewHolder(holder: ChannelsViewHolder, position: Int) {
        val channel = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(channel.image).into(imageView)
            titleTv.text = channel.name_ru
            secondTv.text = channel.current.title
            if (channel.fav) favBtn.setBackgroundResource(R.drawable.ic_baseline_star_blue_24)
            else favBtn.setBackgroundResource(R.drawable.ic_baseline_star_shadow_24)

            favBtn.setOnClickListener {
                if (!channel.fav) favBtn.setBackgroundResource(R.drawable.ic_baseline_star_blue_24)
                else favBtn.setBackgroundResource(R.drawable.ic_baseline_star_shadow_24)
                onBtnClickListener?.let { it(channel) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setOnItemClickListener(listener: (Channel) -> Unit) {
        onBtnClickListener = listener
    }
}