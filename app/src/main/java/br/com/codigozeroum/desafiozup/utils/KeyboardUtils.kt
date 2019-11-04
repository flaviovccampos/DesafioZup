package br.com.codigozeroum.desafiozup.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

class KeyboardUtils {

    companion object{
        fun closeKeyboard(context: Context, viewWithFocus: View){
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(viewWithFocus.windowToken, 0)
        }
    }
}