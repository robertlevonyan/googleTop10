package app.googletop10.domain.usecase

import app.googletop10.entity.ActionResult
import app.googletop10.entity.responses.SearchItem

interface SearchUseCase {
    suspend fun search(text: String): ActionResult<List<SearchItem>>

    suspend fun getSavedSearches(): List<SearchItem>
}
