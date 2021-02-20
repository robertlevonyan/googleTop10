package app.googletop10.data.repository

import app.googletop10.data.api.ApiService
import app.googletop10.data.db.SearchDao
import app.googletop10.domain.repository.SearchRepository
import app.googletop10.entity.responses.SearchItem
import app.googletop10.entity.responses.SearchResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class SearchRepositoryImpl(
    private val apiService: ApiService,
    private val searchDao: SearchDao
) : SearchRepository {
  override fun search(text: String): Flowable<SearchResponse> =
      apiService.search(
          "AIzaSyB_6Y66UjKTf3KGcJYi639TYF8FsayPmas",
          "018298640723871764837:1l6ygmealfg",
          text
      )

  override fun getLastSearchResults(): Flowable<List<SearchItem>> = searchDao.getSearchResults()

  override fun saveSearchResults(results: List<SearchItem>): Completable = searchDao.saveSearchResults(*results.toTypedArray())

  override fun clearSearchResults(): Single<Int> = searchDao.removeSearchResults()
}