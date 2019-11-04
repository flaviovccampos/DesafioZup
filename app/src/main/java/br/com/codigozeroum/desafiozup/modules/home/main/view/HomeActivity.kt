package br.com.codigozeroum.desafiozup.modules.home.main.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import br.com.codigozeroum.desafiozup.R
import br.com.codigozeroum.desafiozup.factory.BaseActivity
import br.com.codigozeroum.desafiozup.modules.home.favorites.view.FavoritesFragment
import br.com.codigozeroum.desafiozup.modules.home.main.viewModel.HomeViewModel
import br.com.codigozeroum.desafiozup.modules.home.search.view.SearchFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        configureView()
        configureViewModel()
        bindView()
    }

    override fun configureView() {

        btnSearch.isSelected = true
        btnSearch.setImageResource(R.drawable.ic_search_black)

        btnSearch.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentPager, SearchFragment()).commit()
            bindTabButtons(0)
        }
        btnFavorites.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentPager, FavoritesFragment()).commit()
            bindTabButtons(1)
        }

        supportFragmentManager.beginTransaction().replace(R.id.fragmentPager, SearchFragment()).commit()
    }

    override fun configureViewModel() {
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    override fun bindView() {
    }

    private fun bindTabButtons(positon: Int){
        when(positon){
            0-> {
                btnSearch.isSelected = true
                btnSearch.setImageResource(R.drawable.ic_search_black)

                btnFavorites.isSelected = false
                btnFavorites.setImageResource(R.drawable.ic_star_big)
            }
            1->{
                btnSearch.isSelected = false
                btnSearch.setImageResource(R.drawable.ic_search)

                btnFavorites.isSelected = true
                btnFavorites.setImageResource(R.drawable.ic_star_big_black)
            }
        }
    }
}
