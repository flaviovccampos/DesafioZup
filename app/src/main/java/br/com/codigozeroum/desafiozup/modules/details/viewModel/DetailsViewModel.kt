package br.com.codigozeroum.desafiozup.modules.details.viewModel

import br.com.codigozeroum.desafiozup.database_realm.model.SearchDetailRealm
import br.com.codigozeroum.desafiozup.factory.BaseViewModel
import br.com.codigozeroum.desafiozup.factory.ViewModelState
import br.com.codigozeroum.desafiozup.modules.details.model.SearchDetailsResponse
import br.com.codigozeroum.desafiozup.modules.details.repository.DetailsLocalRepository
import br.com.codigozeroum.desafiozup.modules.details.repository.DetailsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class DetailsViewModel : BaseViewModel() {

    private val repository = DetailsRepository()
    private val localRepository = DetailsLocalRepository()

    lateinit var itemDetail: SearchDetailsResponse

    var ifExistsSearchDetailRealm = false

    fun getitemDetail(movieId: String) {

        val disposable = repository.getDetails(movieId)
            .subscribe({ result ->

                if (result != null) {
                    itemDetail = result
                    postNewState(ViewModelState.Success)
                } else {
                    postNewState(ViewModelState.Error("Error get Response Details"))
                }

            }, {
                postNewState(ViewModelState.Error(it.message!!))
            })
        addToDisposeBag(disposable)
    }

    fun ifExistsSearchDetailRealm(imdbID: String) {
        ifExistsSearchDetailRealm = localRepository.ifExistsSearchDetailRealm(imdbID)
    }

    fun getTitleFavorite(imdbID: String): String{
        return localRepository.getTitleFavorite(imdbID)
    }

    fun saveSearchDetailRealm(searchDetailsResponse: SearchDetailsResponse, titleFavorite: String) {

        val disposable = localRepository.saveSearchDetailRealm(searchDetailsResponse, titleFavorite)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    postNewState(ViewModelState.SuccessLocal)
                },
                onError = {
                    postNewState(ViewModelState.ErrorLocal)
                })
        addToDisposeBag(disposable)

    }

    fun deleteSearchDetailRealm(imdbID: String) {

        val disposable = localRepository.deleteSearchDetailRealm(imdbID)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    postNewState(ViewModelState.SuccessLocal)
                },
                onError = {
                    postNewState(ViewModelState.ErrorLocal)
                })
        addToDisposeBag(disposable)

    }

}