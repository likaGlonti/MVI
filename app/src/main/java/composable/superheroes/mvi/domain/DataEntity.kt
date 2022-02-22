package composable.superheroes.mvi.domain

import composable.superheroes.mvi.data.DataDTO

data class DataEntity(val title: String, val description: String)

fun DataDTO.toEntity() = DataEntity(title = this.title, description = this.description)
