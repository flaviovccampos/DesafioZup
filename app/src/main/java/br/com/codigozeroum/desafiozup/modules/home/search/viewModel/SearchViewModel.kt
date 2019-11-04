/*
 * SearchViewModelesafioMobile
 *
 * Created by Flavio Campos on 31/08/19 03:18
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiozup.modules.home.search.viewModel

import br.com.codigozeroum.desafiozup.factory.BaseViewModel
import br.com.codigozeroum.desafiozup.R
import br.com.codigozeroum.desafiozup.factory.RecyclerViewDataSource
import br.com.codigozeroum.desafiozup.factory.ViewModelState
import br.com.codigozeroum.desafiozup.modules.home.search.repository.SearchRepository
import br.com.codigozeroum.desafiozup.modules.home.search.model.Search

class SearchViewModel : BaseViewModel(), RecyclerViewDataSource<Search> {

    private val repository = SearchRepository()
    var results: MutableList<Search> = mutableListOf()
    var totalPages = 1

    fun searchMovie(type: String, query: String, page: Int = 1) {
        postNewState(ViewModelState.Loading)
        val disposable = repository.search(type, query, page)
            .subscribe({ result ->
                if (result != null && result.Response == "True") {
                    totalPages = (result.totalResults.toInt() / 10) + 1
                    results = result.Search.toMutableList()
                    postNewState(ViewModelState.Success)
                } else {
                    postNewState(ViewModelState.Error(result.Error))
                }
            }, {
                postNewState(ViewModelState.Error(it.message!!))
            })
        addToDisposeBag(disposable)
    }


    override fun getItemCount(): Int = results.size
    override fun getViewTypeFor(position: Int): Int = R.layout.item_movie_grid
    override fun getItemFor(position: Int): Search = results[position]

}