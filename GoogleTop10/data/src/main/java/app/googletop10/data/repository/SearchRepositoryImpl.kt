package app.googletop10.data.repository

import app.googletop10.data.api.ApiService
import app.googletop10.data.db.SearchDao
import app.googletop10.data.utils.makeApiCall
import app.googletop10.domain.repository.SearchRepository
import app.googletop10.entity.ActionResult
import app.googletop10.entity.responses.SearchItem
import app.googletop10.entity.responses.SearchResponse

class SearchRepositoryImpl(
    private val apiService: ApiService,
    private val searchDao: SearchDao
) : SearchRepository {
    override suspend fun search(text: String): ActionResult<SearchResponse> =
        makeApiCall {
            apiService.search(
                "AIzaSyBpkyNpfmEcR02cBwcKemMv-Ou2a2vPCv0",
                "018298640723871764837:1l6ygmealfg",
                text
            )
        }

    override suspend fun getLastSearchResults(): List<SearchItem> =
        searchDao.getSearchResults()

    override suspend fun saveSearchResults(results: List<SearchItem>) =
        searchDao.saveSearchResults(*results.toTypedArray())

    override suspend fun clearSearchResults() =
        searchDao.removeSearchResults()
}