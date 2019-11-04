package br.com.codigozeroum.desafiozup.modules.home.favorites.viewModel

import androidx.lifecycle.MutableLiveData
import br.com.codigozeroum.desafiozup.R
import br.com.codigozeroum.desafiozup.database_realm.model.SearchDetailRealm
import br.com.codigozeroum.desafiozup.factory.BaseViewModel
import br.com.codigozeroum.desafiozup.factory.RecyclerViewDataSource
import br.com.codigozeroum.desafiozup.modules.home.favorites.repository.FavoritesLocalRepository

class FavoritesViewModel : BaseViewModel(), RecyclerViewDataSource<SearchDetailRealm> {

    private val repository = FavoritesLocalRepository()
    var results: MutableLiveData<MutableList<SearchDetailRealm>> = MutableLiveData()

    fun getFavorites(){
        val res = repository.getAllFavorites().toMutableList()
        results.value = res
    }

    override fun getItemCount(): Int = results.value!!.size
    override fun getViewTypeFor(position: Int): Int  = R.layout.item_movie_grid
    override fun getItemFor(position: Int): SearchDetailRealm = results.value!![position]

}