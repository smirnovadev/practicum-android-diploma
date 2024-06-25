package ru.practicum.android.diploma.search.domain.model

sealed class Resource<T>(val data: T? = null, val message: Int? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: Int) : Resource<T>(null, message)
}
