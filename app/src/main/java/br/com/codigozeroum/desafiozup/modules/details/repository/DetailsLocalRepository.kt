package br.com.codigozeroum.desafiozup.modules.details.repository

import android.graphics.Bitmap
import br.com.codigozeroum.desafiozup.database_realm.RealmHelper
import br.com.codigozeroum.desafiozup.database_realm.model.SearchDetailRealm
import br.com.codigozeroum.desafiozup.modules.details.model.SearchDetailsResponse
import br.com.codigozeroum.desafiozup.utils.ImageUtils
import io.reactivex.Completable

class DetailsLocalRepository {

    private val realmHelper: RealmHelper = RealmHelper()

    fun saveSearchDetailRealm(searchDetailsResponse: SearchDetailsResponse, titleFavorite: String, posterBitmap: Bitmap): Completable {
        val searchDetailRealm = SearchDetailRealm(searchDetailsResponse, titleFavorite)
        searchDetailRealm.PosterBase64 = ImageUtils.bitmapToBase64(posterBitmap)
        searchDetailRealm.id = realmHelper.autoIncrement<SearchDetailRealm>()
        realmHelper.save(searchDetailRealm)
        return Completable.complete()
    }

    fun deleteSearchDetailRealm(imdbID: String): Completable{
        val searchDetailRealm: SearchDetailRealm? = realmHelper.findFirst<SearchDetailRealm>("imdbID", imdbID)
        if(searchDetailRealm != null){
            realmHelper.delete(searchDetailRealm)
        }
        return Completable.complete()
    }

    fun ifExistsSearchDetailRealm(imdbID: String): Boolean{
        return realmHelper.findFirst<SearchDetailRealm>("imdbID", imdbID) != null
    }

    fun getTitleFavorite(imdbID: String): String{
        return if(ifExistsSearchDetailRealm(imdbID)){
            realmHelper.findFirst<SearchDetailRealm>("imdbID", imdbID)!!.titleFavorite
        }else{
            String()
        }

    }

}