package br.com.codigozeroum.desafiozup.database_realm.model

import br.com.codigozeroum.desafiozup.modules.details.model.SearchDetailsResponse
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class SearchDetailRealm() : RealmObject() {

    constructor(searchDetailsResponse: SearchDetailsResponse, titleFavorite: String) : this(){
        this.titleFavorite = titleFavorite
        this.Actors = searchDetailsResponse.Actors
        this.Plot = searchDetailsResponse.Plot
        this.Poster = searchDetailsResponse.Poster
        this.Rated = searchDetailsResponse.Rated
        this.Released = searchDetailsResponse.Released
        this.Response = searchDetailsResponse.Response
        this.Runtime = searchDetailsResponse.Runtime
        this.Title = searchDetailsResponse.Title
        this.Type = searchDetailsResponse.Type
        this.Year = searchDetailsResponse.Year
        this.imdbID = searchDetailsResponse.imdbID
        this.imdbRating = searchDetailsResponse.imdbRating
        this.imdbVotes = searchDetailsResponse.imdbVotes
    }

    @PrimaryKey
    var id: Long? = null

    var titleFavorite: String = String()

    var Actors: String = String()
    var Plot: String = String()
    var Poster: String = String()
    var PosterBase64: String = String()
    var Rated: String = String()
    var Released: String = String()
    var Response: String = String()
    var Runtime: String = String()
    var Title: String = String()
    var Type: String = String()
    var Year: String = String()
    var imdbID: String = String()
    var imdbRating: String = String()
    var imdbVotes: String = String()

}