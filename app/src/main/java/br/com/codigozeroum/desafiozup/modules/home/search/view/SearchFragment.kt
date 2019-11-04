
package br.com.codigozeroum.desafiozup.modules.home.search.view

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import br.com.codigozeroum.desafiozup.*
import br.com.codigozeroum.desafiozup.factory.BaseFragment
import br.com.codigozeroum.desafiozup.factory.RecyclerViewDelegate
import br.com.codigozeroum.desafiozup.factory.ViewModelState
import br.com.codigozeroum.desafiozup.modules.details.view.DetailsActivity
import br.com.codigozeroum.desafiozup.modules.home.search.viewModel.SearchViewModel
import br.com.codigozeroum.desafiozup.modules.home.search.model.Search
import br.com.codigozeroum.desafiozup.modules.home.search.model.SearchAdapter
import br.com.codigozeroum.desafiozup.utils.KeyboardUtils
import br.com.codigozeroum.desafiozup.utils.SpacesItemDecoration
import kotlinx.android.synthetic.main.fragment_search_movie.*
import kotlinx.android.synthetic.main.fragment_search_movie.view.*

class SearchFragment : BaseFragment(), RecyclerViewDelegate<Search> {


    private lateinit var viewModel: SearchViewModel

    val adapter: SearchAdapter by lazy{
        SearchAdapter(viewModel, this@SearchFragment)
    }

    var totalPages = 1
    var currentPage = 1
    var currentQuery = ""
    var rbCheckedValue = "movie"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureView(view)
        configureViewModel()
        bindView()
    }

    override fun configureView(view: View) {
        view.searchGridView.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.margin_10dp)
        view.searchGridView.addItemDecoration(SpacesItemDecoration(spacingInPixels))

        previousPage.setOnClickListener {
            if(currentPage > 1){
                currentPage -= 1
                viewModel.searchMovie(rbCheckedValue, currentQuery, currentPage)

            }
        }
        nextPage.setOnClickListener {
            if(currentPage < totalPages){
                currentPage += 1
                viewModel.searchMovie(rbCheckedValue, currentQuery, currentPage)

            }
        }

        radioGroupType.setOnCheckedChangeListener(object: RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
                val radioButtonChecked = p0?.findViewById<RadioButton>(p1)
                rbCheckedValue = radioButtonChecked?.text.toString().toLowerCase()
                currentPage = 1
            }

        })

        btnSearch.setOnClickListener {
            currentQuery = searchMovieInput.text.toString()
            KeyboardUtils.closeKeyboard(context!!, btnSearch)
            viewModel.searchMovie(rbCheckedValue, currentQuery, currentPage)
        }
    }

    override fun configureViewModel() {
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        viewModel.register(this, Observer { newState->
            when(newState){
                ViewModelState.Success -> {
                    searchGridView.adapter?.notifyDataSetChanged()
                    totalPages = viewModel.totalPages
                    bindView()
                    loading.visibility = View.GONE
                }
                is ViewModelState.Error ->{
                    showSnackBarWith(newState.message)
                    loading.visibility = View.GONE
                }
                ViewModelState.Loading ->{
                    loading.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun bindView() {
        searchGridView.adapter = adapter
        currentPageLabel.text = currentPage.toString()
        totalPagesLabel.text = " de $totalPages"
    }

    override fun onItemClickListener(view: View, position: Int, obj: Search?) {
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra("imdbID", obj!!.imdbID)
        startActivity(intent)
    }


}
