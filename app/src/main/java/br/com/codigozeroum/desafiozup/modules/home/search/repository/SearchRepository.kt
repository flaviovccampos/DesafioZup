package br.com.codigozeroum.desafiozup.modules.home.search.repository

import br.com.codigozeroum.desafiozup.factory.BaseRepository
import br.com.codigozeroum.desafiozup.modules.home.search.model.SearchResponse
import br.com.codigozeroum.desafiozup.services.OMDBServiceApi
import br.com.codigozeroum.desafiozup.network.RetrofitClient
import io.reactivex.Single
import retrofit2.Retrofit

class SearchRepository : BaseRepository(){

    private val retrofitClient: Retrofit
    get() = RetrofitClient.getInstance()

    fun search(type: String, search: String, page: Int): Single<SearchResponse>{
        return doCall(retrofitClient.create(OMDBServiceApi::class.java)
            .search(type, search, page))
    }

}