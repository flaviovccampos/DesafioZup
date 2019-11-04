package br.com.codigozeroum.desafiozup.extensions

import android.content.DialogInterface
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.annotation.ArrayRes
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import br.com.codigozeroum.desafiozup.R
import com.afollestad.materialdialogs.MaterialDialog


fun AppCompatActivity.dialogSaveFavorite(
    favoriteLayout: View,
    callbackPositiveButton: DialogInterface.OnClickListener,
    callbackCancelButton: DialogInterface.OnClickListener
): AlertDialog{

    val builder = AlertDialog.Builder(this)

    builder.setView(favoriteLayout)
        .setPositiveButton("OK", callbackPositiveButton)
        .setNegativeButton("Cancelar", callbackCancelButton)

    return builder.create()
}


fun AppCompatActivity.dialogDeleteFavorite(
    favoriteTitle: String,
    callbackPositiveButton: DialogInterface.OnClickListener,
    callbackCancelButton: DialogInterface.OnClickListener
): AlertDialog{

    val builder = AlertDialog.Builder(this)

    builder
        .setTitle("Desaja excluir Favorito?")
        .setMessage(favoriteTitle)
        .setPositiveButton("Excluir", callbackPositiveButton)
        .setNegativeButton("Cancelar", callbackCancelButton)

    return builder.create()
}



/*fun AppCompatActivity.dialogSaveFavorite(
        @ColorInt primaryColor: Int,
        @ColorInt secondaryColor: Int,
        itemSelected: Int,
        listener: MaterialDialog.ListCallbackSingleChoice
): MaterialDialog {

    return MaterialDialog.Builder(this)
            .title(title)
            .items(items)
            .positiveColor(primaryColor)
            .negativeColor(primaryColor)
            .widgetColor(primaryColor)
            .titleColor(secondaryColor)
            .positiveText(R.string.hiae_dialog_positive)
            .negativeText(R.string.hiae_dialog_negative)
            .itemsCallbackSingleChoice(itemSelected, listener)
            .itemsColor(primaryColor)
            .show()
}*/

fun AppCompatActivity.dialogMessage(
        @StringRes title: Int,
        @StringRes content: Int,
        @StringRes positiveText: Int,
        @StringRes negativeText: Int? = null,
        callbackPositive: MaterialDialog.SingleButtonCallback? = null,
        callbackNegative: MaterialDialog.SingleButtonCallback? = null,
        cancelable: Boolean? = null
): MaterialDialog {

    var materialDialog = MaterialDialog.Builder(this)
            .title(title)
            .titleColor(ContextCompat.getColor(this, R.color.colorPrimary))
            .content(content)
            .positiveColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
            .negativeColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
            .positiveText(positiveText)

    negativeText?.let { materialDialog.negativeText(it) }
    callbackPositive?.let { materialDialog.onPositive(it) }
    callbackNegative?.let { materialDialog.onNegative(it) }
    cancelable?.let { materialDialog.cancelable(it) }

    return materialDialog.show()
}

fun AppCompatActivity.dialogMessage(
        title: String,
        content: String,
        @StringRes positiveText: Int,
        @StringRes negativeText: Int? = null,
        callbackPositive: MaterialDialog.SingleButtonCallback? = null,
        callbackNegative: MaterialDialog.SingleButtonCallback? = null,
        cancelable: Boolean? = null
): MaterialDialog {
    var materialDialog = MaterialDialog.Builder(this)
            .title(title)
            .titleColor(ContextCompat.getColor(this, R.color.colorPrimary))
            .content(content)
            .positiveColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
            .negativeColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
            .positiveText(positiveText)

    negativeText?.let { materialDialog.negativeText(it) }
    callbackPositive?.let { materialDialog.onPositive(it) }
    callbackNegative?.let { materialDialog.onNegative(it) }
    cancelable?.let { materialDialog.cancelable(it) }

    return materialDialog.show()
}
