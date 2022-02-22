package composable.superheroes.mvi.presentation.state

sealed class DataState<out S> : BaseState {
    data class Success<out S>(val data: S) : DataState<S>()
    data class Error(val exception: Exception) : DataState<Nothing>()
    data class Loading(val isLoading: Boolean) : DataState<Nothing>()
}

