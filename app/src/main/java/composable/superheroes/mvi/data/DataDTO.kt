package composable.superheroes.mvi.data

import composable.superheroes.mvi.domain.DataEntity

data class DataDTO(val title: String, val description: String)

fun DataEntity.toDTO() = DataDTO(title = this.title, description = this.description)
