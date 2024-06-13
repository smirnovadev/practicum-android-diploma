package ru.practicum.android.diploma.job.domain



sealed interface JobScreenState {
    data object Loading : JobScreenState

    data object Error : JobScreenState

    data object Content : JobScreenState // дописать
}
