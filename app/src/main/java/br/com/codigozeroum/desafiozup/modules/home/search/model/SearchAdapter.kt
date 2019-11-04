package br.com.codigozeroum.desafiozup.modules.home.search.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.codigozeroum.desafiozup.R
import br.com.codigozeroum.desafiozup.factory.RecyclerViewDataSource
import br.com.codigozeroum.desafiozup.factory.RecyclerViewDelegate

class SearchAdapter(private val dataSource : RecyclerViewDataSource<Search>, private val delegate : RecyclerViewDelegate<Search>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_movie_grid, parent, false)
        return SearchViewHolder(view, delegate)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SearchViewHolder) {
            holder.bind(dataSource.getItemFor(position))
        }
    }

    override fun getItemCount(): Int = dataSource.getItemCount()


}