package composable.superheroes.mvi.presentation.intent

import composable.superheroes.mvi.domain.DataEntity


sealed class DataIntent : BaseIntent {
    data class SaveData(val data: DataEntity, val index: Int) : DataIntent()
    object GetAll : DataIntent()
    data class EditDataItem(val position: Int) : DataIntent()
}
