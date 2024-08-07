package ru.practicum.android.diploma.search.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentSearchBinding
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.search.presentation.SearchScreenState
import ru.practicum.android.diploma.search.ui.adapter.SearchAdapter
import ru.practicum.android.diploma.util.Formatter

class SearchFragmentStateHandler(
    private val binding: FragmentSearchBinding,
    private val searchAdapter: SearchAdapter,
    private val context: Context
) {
    fun renderState(state: SearchScreenState) {
        when (state) {
            is SearchScreenState.Default -> showDefaultScreenState()
            is SearchScreenState.Loading -> showProgressbar()
            is SearchScreenState.InternetConnectionError -> showInternetConnectionError()
            is SearchScreenState.ServerError -> showServerError()
            is SearchScreenState.SearchError -> showSearchError()
            is SearchScreenState.ShowContent -> showContent(state.vacancies, state.found)
            is SearchScreenState.UploadNextPage -> uploadNextPage()
            is SearchScreenState.Error -> showError()
            is SearchScreenState.IOError -> showError()
            is SearchScreenState.UploadingError -> {
                Toast
                    .makeText(context, context.getString(R.string.error_occured), Toast.LENGTH_SHORT)
                    .show()
                showUploadingError(state.vacancies, state.found)
            }

            is SearchScreenState.UploadingInternetError -> {
                Toast
                    .makeText(context, context.getString(R.string.check_internet_connection), Toast.LENGTH_SHORT)
                    .show()
                showUploadingError(state.vacancies, state.found)
            }
        }
    }

    private fun showProgressbar() {
        binding.apply {
            searchScreenCover.isVisible = false
            progressBar.isVisible = true
            errorPlaceholder.isVisible = false
            searchStatus.isVisible = false
            recyclerView.isVisible = false
        }
    }

    private fun showInternetConnectionError() {
        binding.apply {
            searchScreenCover.isVisible = false
            progressBar.isVisible = false
            errorPlaceholder.isVisible = true
            errorPlaceholder.setText(R.string.no_internet)
            errorPlaceholder.setCompoundDrawablesWithIntrinsicBounds(
                0,
                R.drawable.img_internet_connection_error,
                0,
                0
            )
            searchStatus.isVisible = false
            recyclerView.isVisible = false
        }
        hideKeyboard(context, binding.searchField)
    }

    private fun showServerError() {
        binding.apply {
            searchScreenCover.isVisible = false
            progressBar.isVisible = false
            errorPlaceholder.isVisible = true
            errorPlaceholder.setText(R.string.server_error)
            errorPlaceholder.setCompoundDrawablesWithIntrinsicBounds(
                0,
                R.drawable.img_server_error_placeholder,
                0,
                0
            )
            searchStatus.isVisible = false
            recyclerView.isVisible = false
        }
        hideKeyboard(context, binding.searchField)
    }

    private fun showSearchError() {
        binding.apply {
            searchScreenCover.isVisible = false
            progressBar.isVisible = false
            errorPlaceholder.isVisible = true
            errorPlaceholder.setText(R.string.empty_search_results)
            errorPlaceholder.setCompoundDrawablesWithIntrinsicBounds(
                0,
                R.drawable.img_empty_search_results,
                0,
                0
            )
            searchStatus.isVisible = true
            searchStatus.text = context.getString(R.string.no_such_vacancies)
            recyclerView.isVisible = false
        }
        hideKeyboard(context, binding.searchField)
    }

    private fun showDefaultScreenState() {
        binding.apply {
            searchScreenCover.isVisible = true
            progressBar.isVisible = false
            errorPlaceholder.isVisible = false
            searchStatus.isVisible = false
            recyclerView.isVisible = false
        }
        hideKeyboard(context, binding.searchField)
    }

    private fun uploadNextPage() {
        binding.apply {
            errorPlaceholder.isVisible = false
            progressBar.isVisible = true
        }
    }

    private fun showError() {
        Toast.makeText(context, context.getString(R.string.error_occured), Toast.LENGTH_SHORT).show()
        binding.apply {
            searchScreenCover.isVisible = true
            progressBar.isVisible = false
            errorPlaceholder.isVisible = false
            searchStatus.isVisible = false
            recyclerView.isVisible = false
        }
        hideKeyboard(context, binding.searchField)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showContent(list: ArrayList<Vacancy>, resultsQty: Int) {
        searchAdapter.vacanciesList.clear()
        searchAdapter.vacanciesList.addAll(list)
        searchAdapter.notifyDataSetChanged()
        binding.apply {
            searchScreenCover.isVisible = false
            progressBar.isVisible = false
            errorPlaceholder.isVisible = false
            searchStatus.isVisible = true
            searchStatus.text = getVacanciesWordForm(resultsQty)
            recyclerView.isVisible = true
        }
        hideKeyboard(context, binding.searchField)
    }

    private fun showUploadingError(list: ArrayList<Vacancy>, resultsQty: Int) {
        binding.apply {
            searchScreenCover.isVisible = false
            progressBar.isVisible = false
            errorPlaceholder.isVisible = false
            searchStatus.isVisible = true
            searchStatus.text = getVacanciesWordForm(resultsQty)
            recyclerView.isVisible = true
        }
        hideKeyboard(context, binding.searchField)
    }

    private fun getVacanciesWordForm(quantity: Int): String {
        val vacancyForm = Formatter
            .quantityWordFormFormatter(
                quantity,
                context.getString(R.string.vacancy),
                context.getString(R.string.vacancy_genitive),
                context.getString(R.string.vacancies_genitive)
            )

        val foundForm = Formatter
            .quantityWordFormFormatter(
                quantity,
                context.getString(R.string.found_f),
                context.getString(R.string.found_f_genitive),
                context.getString(R.string.found_f_genitive_pl)
            )

        return "$foundForm $quantity $vacancyForm"
    }

    private fun hideKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
