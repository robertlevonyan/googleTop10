package app.googletop10.domain.usecase

import app.googletop10.entity.responses.SearchItem
import io.reactivex.Flowable

interface SearchUseCase {
  fun search(text: String): Flowable<List<SearchItem>>

  fun getSavedSearches(): Flowable<List<SearchItem>>
}
