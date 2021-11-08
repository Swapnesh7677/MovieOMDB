package com.triologic.jewel_cliq.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.swapnesh.appmovie.R
import com.swapnesh.appmovie.databinding.ItemMovieBinding
import com.swapnesh.appmovie.interfaces.OnListBsItemClickListener
import com.swapnesh.movies.view.ui.model.DataItem


class ItemListAdapter(
    val dataList: ArrayList<DataItem>,
    val listener: OnListBsItemClickListener
) :
    RecyclerView.Adapter<ItemListAdapter.ViewMaker>() {
    var ctx: Context? = null

    inner class ViewMaker(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMovieBinding.bind(itemView)

        init {
            binding.cardMovie.setOnClickListener {
               val selectedIndex = adapterPosition
                listener.onListBsItemClicked(selectedIndex)
                notifyDataSetChanged()
            }
        }

        fun bind() {
            val txt = dataList[adapterPosition]
            binding.title.text = txt.title
            binding.type.text = txt.type
            binding.time.text = txt.year
            Glide.with(ctx!!)
                .load(txt.poster)
                .into(binding.movieImg);

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewMaker {
        ctx = parent.context
        return ViewMaker(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewMaker, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = dataList.size
}