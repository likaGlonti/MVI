package composable.superheroes.mvi.presentation.state

import androidx.lifecycle.LiveData
import composable.superheroes.mvi.presentation.intent.BaseIntent

interface Model<S : BaseState, I : BaseIntent> {
    fun intent(intentType: I)
    val state: LiveData<S>
}