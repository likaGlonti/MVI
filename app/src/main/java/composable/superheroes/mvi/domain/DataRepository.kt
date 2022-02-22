package composable.superheroes.mvi.domain

interface DataRepository {
    fun getAll(): List<DataEntity>
    fun getByIndex(index: Int): DataEntity
    fun saveData(data: DataEntity, index: Int)
}