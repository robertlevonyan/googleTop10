package app.googletop10.domain.usecase

import app.googletop10.domain.repository.SearchRepository
import app.googletop10.entity.responses.SearchItem
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class SearchUseCaseImpl(private val searchRepository: SearchRepository) : SearchUseCase {

  override fun search(text: String): Flowable<List<SearchItem>> {
    if (text.trim().isEmpty()) return Flowable.empty()
    return searchRepository.search(text)
        .subscribeOn(Schedulers.io())
        .map { it.searchItems }
  }

  override fun getSavedSearches(): Flowable<List<SearchItem>> = searchRepository.getLastSearchResults()
}
