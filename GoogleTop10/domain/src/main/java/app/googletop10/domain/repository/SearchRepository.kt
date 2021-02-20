package app.googletop10.domain.repository

import app.googletop10.entity.responses.SearchItem
import app.googletop10.entity.responses.SearchResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface SearchRepository {
  fun search(text: String): Flowable<SearchResponse>

  fun getLastSearchResults(): Flowable<List<SearchItem>>

  fun saveSearchResults(results: List<SearchItem>): Completable

  fun clearSearchResults(): Single<Int>
}
