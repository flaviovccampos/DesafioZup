package br.com.codigozeroum.desafiozup.factory

interface RecyclerViewDataSource<out T> {
    fun getItemCount(): Int
    fun getViewTypeFor(position: Int): Int
    fun getItemFor(position: Int): T
}