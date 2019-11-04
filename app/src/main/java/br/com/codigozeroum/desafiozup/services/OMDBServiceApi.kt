package br.com.codigozeroum.desafiozup.services

import br.com.codigozeroum.desafiozup.modules.details.model.SearchDetailsResponse
import br.com.codigozeroum.desafiozup.modules.home.search.model.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDBServiceApi {

    @GET("/?apikey=7a4c5cac")
    fun search(@Query("type") type: String, @Query("s") search: String, @Query("page") page: Int): Single<SearchResponse>

    @GET("/?apikey=7a4c5cac")
    fun getItemDetails(@Query("i") imdbID: String): Single<SearchDetailsResponse>
}