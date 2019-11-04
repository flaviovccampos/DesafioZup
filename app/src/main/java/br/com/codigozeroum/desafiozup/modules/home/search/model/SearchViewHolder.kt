package br.com.codigozeroum.desafiozup.modules.home.search.model

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.codigozeroum.desafiozup.utils.ImageLoader
import br.com.codigozeroum.desafiozup.factory.RecyclerViewDelegate
import kotlinx.android.synthetic.main.item_movie_grid.view.*

class SearchViewHolder(itemView: View, private val delegate: RecyclerViewDelegate<Search>): RecyclerView.ViewHolder(itemView) {

    @SuppressLint("NewApi")
    fun bind(item: Search){
        ImageLoader.loadImageWith(itemView.context, item.Poster, itemView.folderImage)
        itemView.releaseDate.text = item.Year
        itemView.title.text = item.Title

        itemView.setOnClickListener { delegate.onItemClickListener(itemView, adapterPosition, item) }

    }
}