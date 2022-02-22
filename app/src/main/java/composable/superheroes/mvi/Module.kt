package composable.superheroes.mvi

import composable.superheroes.mvi.data.DataRepositoryImpl
import composable.superheroes.mvi.domain.DataRepository

object Module {
    val repository: DataRepository by lazy {
        DataRepositoryImpl()
    }
}