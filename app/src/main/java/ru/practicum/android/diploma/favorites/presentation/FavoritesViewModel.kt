package ru.practicum.android.diploma.favorites.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.favorites.domain.FavoritesInteractor
import ru.practicum.android.diploma.favorites.domain.models.FavoritesScreenState

class FavoritesViewModel(private val interactor: FavoritesInteractor) : ViewModel() {

    private val screenState = MutableLiveData<FavoritesScreenState>(FavoritesScreenState.NoFavoritesAdded)

    fun getScreenState(): LiveData<FavoritesScreenState> = screenState

    init {
        getVacancies()
    }

    fun getVacancies() {
        viewModelScope.launch {
            screenState.postValue(interactor.loadData())
        }
    }

}
