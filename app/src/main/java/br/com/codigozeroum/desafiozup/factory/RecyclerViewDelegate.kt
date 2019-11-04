
package br.com.codigozeroum.desafiozup.factory

import android.view.View

interface RecyclerViewDelegate <in T> {
    fun onItemClickListener(view: View, position: Int, obj: T? )
}
