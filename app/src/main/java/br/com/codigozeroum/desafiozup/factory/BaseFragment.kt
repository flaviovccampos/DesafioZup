package br.com.codigozeroum.desafiozup.factory

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import br.com.codigozeroum.desafiozup.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment(){

    protected abstract fun configureView(view: View)
    protected abstract fun configureViewModel()
    protected abstract fun bindView()


    private val snackBar: Snackbar by lazy {

        Snackbar.make(activity!!.findViewById(android.R.id.content), "", Snackbar.LENGTH_INDEFINITE)
            .apply {
                val color = ContextCompat.getColor(
                    this.context,
                    R.color.colorPrimary
                )
                view.setBackgroundColor(color)
                setActionTextColor(Color.WHITE)
            }
    }

    protected fun showSnackBarWith(message: String, completion: (() -> Unit)? = null) {
        snackBar.setText(message)
        snackBar.setAction(getString(R.string.ok)) {
            completion?.invoke()
            snackBar.dismiss()
        }
        snackBar.show()
    }
}