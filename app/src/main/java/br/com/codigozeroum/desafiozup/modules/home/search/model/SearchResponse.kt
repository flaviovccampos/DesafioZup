package br.com.codigozeroum.desafiozup.modules.home.search.model

data class SearchResponse(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String,
    val Error: String
)