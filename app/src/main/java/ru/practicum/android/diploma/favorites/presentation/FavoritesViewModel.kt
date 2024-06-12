package ru.practicum.android.diploma.favorites.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.favorites.domain.models.FavoritesScreenState

class FavoritesViewModel : ViewModel() {

    private val screenState = MutableLiveData<FavoritesScreenState>(FavoritesScreenState.Default)
    fun getScreenState(): LiveData<FavoritesScreenState> = screenState

    init {
        screenState.postValue(FavoritesScreenState.NoFavoritesAdded) // изменить State, когда будет готова база данных
    }

}