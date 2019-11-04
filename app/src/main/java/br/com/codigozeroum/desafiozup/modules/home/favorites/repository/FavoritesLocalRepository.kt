package br.com.codigozeroum.desafiozup.modules.home.favorites.repository

import br.com.codigozeroum.desafiozup.database_realm.RealmHelper
import br.com.codigozeroum.desafiozup.database_realm.model.SearchDetailRealm

class FavoritesLocalRepository{

    private val realmHelper: RealmHelper = RealmHelper()

    fun getAllFavorites(): List<SearchDetailRealm>{
        val results: List<SearchDetailRealm> = realmHelper.findAll()
        return results
    }
}