package br.com.codigozeroum.desafiozup.modules.details.repository

import br.com.codigozeroum.desafiozup.factory.BaseRepository
import br.com.codigozeroum.desafiozup.modules.details.model.SearchDetailsResponse
import br.com.codigozeroum.desafiozup.services.OMDBServiceApi
import br.com.codigozeroum.desafiozup.network.RetrofitClient
import io.reactivex.Single
import retrofit2.Retrofit

class DetailsRepository : BaseRepository() {

    private val retrofitClient: Retrofit
        get() = RetrofitClient.getInstance()

    fun getDetails( imdbID: String): Single<SearchDetailsResponse>{
        return doCall( retrofitClient.create(OMDBServiceApi::class.java).getItemDetails(imdbID) )
    }
}