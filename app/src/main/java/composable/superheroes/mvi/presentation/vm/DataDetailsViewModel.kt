package composable.superheroes.mvi.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import composable.superheroes.mvi.domain.DataEntity
import composable.superheroes.mvi.domain.DataRepository
import composable.superheroes.mvi.presentation.intent.DataIntent
import composable.superheroes.mvi.presentation.state.DataState
import composable.superheroes.mvi.presentation.state.Model

class DataDetailsViewModel(private val repository: DataRepository) : ViewModel(),
    Model<DataState<DataEntity>, DataIntent> {

    private var _state = MutableLiveData<DataState<DataEntity>>()
    override val state: LiveData<DataState<DataEntity>>
        get() = _state

    override fun intent(intentType: DataIntent) {
        when (intentType) {
            is DataIntent.EditDataItem -> getItemByIndex(intentType.position)
            is DataIntent.SaveData -> saveData(intentType.data, intentType.index)
        }
    }

    private fun saveData(data: DataEntity, index: Int) {
        repository.saveData(data, index)
    }

    private fun getItemByIndex(index: Int) {
        _state.value = DataState.Success(repository.getByIndex(index))
    }


}