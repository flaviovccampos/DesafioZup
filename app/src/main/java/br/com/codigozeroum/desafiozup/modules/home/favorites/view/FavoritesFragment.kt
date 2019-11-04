package br.com.codigozeroum.desafiozup.modules.home.favorites.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import br.com.codigozeroum.desafiozup.factory.BaseFragment
import br.com.codigozeroum.desafiozup.R
import br.com.codigozeroum.desafiozup.database_realm.model.SearchDetailRealm
import br.com.codigozeroum.desafiozup.factory.RecyclerViewDelegate
import br.com.codigozeroum.desafiozup.modules.details.view.DetailsActivity
import br.com.codigozeroum.desafiozup.modules.home.favorites.viewModel.FavoritesViewModel
import br.com.codigozeroum.desafiozup.modules.home.favorites.model.FavoritesAdapter
import br.com.codigozeroum.desafiozup.utils.SpacesItemDecoration
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.fragment_favorites.view.*
import kotlinx.android.synthetic.main.fragment_search_movie.*

class FavoritesFragment : BaseFragment() , RecyclerViewDelegate<SearchDetailRealm> {

    private lateinit var viewModel: FavoritesViewModel

    val adapter: FavoritesAdapter by lazy{
        FavoritesAdapter(viewModel, this@FavoritesFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureView(view)
        configureViewModel()

    }

    override fun configureView(view: View) {
        view.favoritesGridView.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.margin_10dp)
        view.favoritesGridView.addItemDecoration(SpacesItemDecoration(spacingInPixels))
    }

    override fun configureViewModel() {
        viewModel = ViewModelProviders.of(this).get(FavoritesViewModel::class.java)
        viewModel.results.observe(this, Observer {
            adapter.notifyDataSetChanged()
            bindView()
        })
        viewModel.getFavorites()
    }

    override fun bindView() {
        favoritesGridView.adapter = adapter
    }


    override fun onItemClickListener(view: View, position: Int, obj: SearchDetailRealm?) {
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra("imdbID", obj!!.imdbID)
        startActivity(intent)
    }

}
