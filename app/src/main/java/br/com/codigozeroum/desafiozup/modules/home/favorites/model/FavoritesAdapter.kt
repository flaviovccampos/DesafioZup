package br.com.codigozeroum.desafiozup.modules.home.favorites.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.codigozeroum.desafiozup.R
import br.com.codigozeroum.desafiozup.database_realm.model.SearchDetailRealm
import br.com.codigozeroum.desafiozup.factory.RecyclerViewDataSource
import br.com.codigozeroum.desafiozup.factory.RecyclerViewDelegate

class FavoritesAdapter(
    private val dataSource : RecyclerViewDataSource<SearchDetailRealm>, private val delegate : RecyclerViewDelegate<SearchDetailRealm>
): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_movie_grid, parent, false)
        return FavoritesViewHolder(view, delegate)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FavoritesViewHolder) {
            holder.bind(dataSource.getItemFor(position))
        }
    }

    override fun getItemCount(): Int = dataSource.getItemCount()

}