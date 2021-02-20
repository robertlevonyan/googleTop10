package app.googletop10.presentation.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.googletop10.domain.usecase.SearchUseCase
import app.googletop10.entity.responses.SearchItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SearchViewModel(private val searchUseCase: SearchUseCase) : ViewModel() {
  private var searchInput = ""
  private val compositeDisposable = CompositeDisposable()

  private val _onSearchCompleted by lazy { MutableLiveData<List<SearchItem>>() }
  val onSearchCompleted: LiveData<List<SearchItem>> get() = _onSearchCompleted

  private val _onSearchError by lazy { MutableLiveData<Unit>() }
  val onSearchError: LiveData<Unit> get() = _onSearchError

  init {
    viewModelScope.launch(Dispatchers.IO) {
      searchUseCase.getSavedSearches()
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(
              { _onSearchCompleted.postValue(it) },
              { _onSearchError.postValue(Unit) },
          )
          .also { compositeDisposable.add(it) }
    }
  }

  fun onSearchInput(input: String) {
    searchInput = input
  }

  fun performSearch() {
    searchUseCase.search(searchInput)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { _onSearchCompleted.postValue(it) },
            { _onSearchError.postValue(Unit) },
        )
        .also { compositeDisposable.add(it) }
  }

  override fun onCleared() {
    super.onCleared()

    if (compositeDisposable.isDisposed) return
    compositeDisposable.dispose()
  }
}
