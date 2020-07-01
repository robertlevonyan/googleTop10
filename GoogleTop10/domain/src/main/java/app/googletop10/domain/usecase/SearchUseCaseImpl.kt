package app.googletop10.domain.usecase

import android.util.Log
import app.googletop10.domain.repository.SearchRepository
import app.googletop10.entity.ActionResult
import app.googletop10.entity.responses.SearchItem

class SearchUseCaseImpl(private val searchRepository: SearchRepository) : SearchUseCase {

    override suspend fun search(text: String): ActionResult<List<SearchItem>> {
        if (text.trim().isEmpty()) return ActionResult.Success(emptyList())
        return searchRepository.search(text).let {
            when (it) {
                is ActionResult.Success -> {
                    searchRepository.clearSearchResults()
                    it.data.searchItems.let { items ->
                        searchRepository.saveSearchResults(items)
                        ActionResult.Success(items)
                    }
                }
                is ActionResult.Error -> {
                    Log.e("SEARCH", "ERROR", it.exception)
                    ActionResult.Error(it.exception)
                }
            }
        }
    }

    override suspend fun getSavedSearches(): List<SearchItem> =
        searchRepository.getLastSearchResults()
}
