package app.googletop10.domain.repository

import app.googletop10.entity.ActionResult
import app.googletop10.entity.responses.SearchItem
import app.googletop10.entity.responses.SearchResponse

interface SearchRepository {
    suspend fun search(text: String): ActionResult<SearchResponse>

    suspend fun getLastSearchResults(): List<SearchItem>

    suspend fun saveSearchResults(results: List<SearchItem>)

    suspend fun clearSearchResults()
}
