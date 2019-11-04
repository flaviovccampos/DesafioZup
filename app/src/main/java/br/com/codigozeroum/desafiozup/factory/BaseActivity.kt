package br.com.codigozeroum.desafiozup.factory

import android.graphics.Color
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.com.codigozeroum.desafiozup.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity : AppCompatActivity() {

    protected abstract fun configureView()
    protected abstract fun configureViewModel()
    protected abstract fun bindView()

    private val snackBar: Snackbar by lazy {

        Snackbar.make(findViewById(android.R.id.content), "", Snackbar.LENGTH_INDEFINITE)
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

    fun toast( message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}