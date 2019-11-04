package br.com.codigozeroum.desafiozup.modules.home.favorites.model

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.codigozeroum.desafiozup.database_realm.model.SearchDetailRealm
import br.com.codigozeroum.desafiozup.factory.RecyclerViewDelegate
import br.com.codigozeroum.desafiozup.utils.ImageLoader
import kotlinx.android.synthetic.main.item_movie_grid.view.*

class FavoritesViewHolder(itemView: View, private val delegate: RecyclerViewDelegate<SearchDetailRealm>) : RecyclerView.ViewHolder(itemView) {

    @SuppressLint("NewApi")
    fun bind(item: SearchDetailRealm) {
        ImageLoader.loadImageWith(itemView.context, item.Poster, itemView.folderImage)
        itemView.releaseDate.text = item.Year
        itemView.title.text = item.Title

        itemView.title_favorite.visibility = View.VISIBLE
        itemView.title_favorite.text = item.titleFavorite

        itemView.setOnClickListener { delegate.onItemClickListener(itemView, adapterPosition, item) }
    }
}