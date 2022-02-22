package composable.superheroes.mvi.data

import composable.superheroes.mvi.domain.DataEntity
import composable.superheroes.mvi.domain.DataRepository
import composable.superheroes.mvi.domain.toEntity

class DataRepositoryImpl : DataRepository {
    override fun getAll(): List<DataEntity> {
        return Data.getAll().map { it.toEntity() }
    }

    override fun getByIndex(index: Int): DataEntity {
        return Data.get(index).toEntity()
    }

    override fun saveData(data: DataEntity, index: Int) {
        Data.deleteAt(index)
        Data.addData(data.toDTO())
    }

}