package br.com.codigozeroum.desafiozup.factory

sealed class ViewModelState {
    data class Error(val message: String) : ViewModelState()
    object ErrorLocal : ViewModelState()
    object Loading : ViewModelState()
    object Success : ViewModelState()
    object SuccessLocal : ViewModelState()
}
