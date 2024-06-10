package ru.practicum.android.diploma.data.network

sealed class Resource<T>(val data: T? = null, val message: Int? = null) {
    class Success<T>(data: T) : Resource<T>(data, null)
    class Error<T>(message: Int) : Resource<T>(null, message)
}
