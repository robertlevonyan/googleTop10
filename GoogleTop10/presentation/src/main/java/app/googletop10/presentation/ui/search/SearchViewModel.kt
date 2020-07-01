package app.googletop10.presentation.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.googletop10.domain.usecase.SearchUseCase
import app.googletop10.entity.ActionResult
import app.googletop10.entity.responses.SearchItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(private val searchUseCase: SearchUseCase) : ViewModel() {

    private var searchInput = ""

    private val _onSearchCompleted by lazy { MutableLiveData<List<SearchItem>>() }
    val onSearchCompleted: LiveData<List<SearchItem>> get() = _onSearchCompleted

    private val _onSearchError by lazy { MutableLiveData<Unit>() }
    val onSearchError: LiveData<Unit> get() = _onSearchError

    init {
        viewModelScope.launch(Dispatchers.IO) {
            searchUseCase.getSavedSearches()
                .takeIf { it.isNotEmpty() }
                ?.let { _onSearchCompleted.postValue(it) }
        }
    }

    fun onSearchInput(input: String) {
        searchInput = input
    }

    fun performSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            searchUseCase.search(searchInput).let {
                when (it) {
                    is ActionResult.Success -> _onSearchCompleted.postValue(it.data)
                    is ActionResult.Error -> _onSearchError.postValue(Unit)
                }
            }
        }
    }
}