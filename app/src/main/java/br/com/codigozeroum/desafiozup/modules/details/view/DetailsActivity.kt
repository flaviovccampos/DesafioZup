package br.com.codigozeroum.desafiozup.modules.details.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import br.com.codigozeroum.desafiozup.factory.BaseActivity
import br.com.codigozeroum.desafiozup.R
import br.com.codigozeroum.desafiozup.extensions.dialogDeleteFavorite
import br.com.codigozeroum.desafiozup.extensions.dialogSaveFavorite
import br.com.codigozeroum.desafiozup.factory.ViewModelState
import br.com.codigozeroum.desafiozup.modules.details.viewModel.DetailsViewModel
import br.com.codigozeroum.desafiozup.utils.ImageLoader
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : BaseActivity() {

    lateinit var viewModel: DetailsViewModel

    private var newFavoriteTitle = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        configureView()
        configureViewModel()
    }

    override fun configureView() {
        closeButton.setOnClickListener { this.finish() }

        //botao adicionar/remover
        btn_addFavorites.setOnClickListener {
            if (viewModel.ifExistsSearchDetailRealm) {

                this.dialogDeleteFavorite(
                    newFavoriteTitle,
                    DialogInterface.OnClickListener { dialog, id ->
                        viewModel.deleteSearchDetailRealm(viewModel.itemDetail.imdbID)
                    }
                    , DialogInterface.OnClickListener { dialog, id ->
                        dialog.dismiss()
                    }

                ).show()


            } else {

                val inflater: LayoutInflater = this.layoutInflater
                val favoriteLayout = inflater.inflate(R.layout.dialog_save_favorite, null)

                this.dialogSaveFavorite(
                    favoriteLayout,
                    DialogInterface.OnClickListener { dialog, id ->
                        val editText: EditText = favoriteLayout.findViewById(R.id.favorite_title)
                        newFavoriteTitle = editText.text.toString()
                        viewModel.saveSearchDetailRealm(viewModel.itemDetail, newFavoriteTitle)
                    }
                    , DialogInterface.OnClickListener { dialog, id ->
                        dialog.dismiss()
                    }

                ).show()

            }
        }
    }

    override fun configureViewModel() {
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)

        viewModel.register(this, Observer { newState ->
            when (newState) {
                is ViewModelState.Success -> {
                    bindView()
                }
                is ViewModelState.SuccessLocal -> {
                    showSnackBarWith("Favoritos Atualizado com Sucesso!!")
                    onSuccessSaveDeleteLocal()
                }
                is ViewModelState.Error -> {
                    toast(newState.message)
                }
                is ViewModelState.ErrorLocal -> {
                    showSnackBarWith("Erro ao salvar Favorito!")
                }
            }
        })

        val imdbID = intent.extras?.getString("imdbID") ?: String()
        if (imdbID == String()) {
            toast("Não foi possivel carregar! Tente novamente.")
            this.finish()
        } else {
            viewModel.ifExistsSearchDetailRealm(imdbID)
            viewModel.getitemDetail(imdbID)
        }

    }

    override fun bindView() {
        ImageLoader.loadImageWith(this, viewModel.itemDetail.Poster, poster)
        movieTitle.text = viewModel.itemDetail.Title
        year.text = viewModel.itemDetail.Year
        voteAverage.text = viewModel.itemDetail.imdbVotes
        popularity.text = viewModel.itemDetail.imdbRating
        othersInfo.text = "${viewModel.itemDetail.Runtime} | ${viewModel.itemDetail.Genre}"
        sinopse.text = viewModel.itemDetail.Plot

        if (viewModel.ifExistsSearchDetailRealm) {
            btn_addFavorites.text = "- Favoritos"
            newFavoriteTitle = viewModel.getTitleFavorite(viewModel.itemDetail.imdbID)
            label_title_favorite.text = newFavoriteTitle
        } else {
            btn_addFavorites.text = "+ Favoritos"
        }
    }

    private fun onSuccessSaveDeleteLocal(){
        if(btn_addFavorites.text == "+ Favoritos") {
            btn_addFavorites.text = "- Favoritos"
            label_title_favorite.text = newFavoriteTitle
        }else {
            btn_addFavorites.text = "+ Favoritos"
            label_title_favorite.text = String()
        }


    }
}
