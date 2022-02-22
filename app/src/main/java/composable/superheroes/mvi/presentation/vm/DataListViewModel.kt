package composable.superheroes.mvi.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import composable.superheroes.mvi.domain.DataEntity
import composable.superheroes.mvi.domain.DataRepository
import composable.superheroes.mvi.presentation.intent.DataIntent
import composable.superheroes.mvi.presentation.state.DataState
import composable.superheroes.mvi.presentation.state.Model

class DataListViewModel(private val repository: DataRepository) : ViewModel(),
    Model<DataState<List<DataEntity>>, DataIntent> {

    private val _state = MutableLiveData<DataState<List<DataEntity>>>()
    override val state: LiveData<DataState<List<DataEntity>>>
        get() = _state

    private fun getAllData() {
        _state.value = DataState.Success(repository.getAll())
    }

    override fun intent(intentType: DataIntent) {
        when (intentType) {
            is DataIntent.GetAll -> getAllData()
        }
    }
}