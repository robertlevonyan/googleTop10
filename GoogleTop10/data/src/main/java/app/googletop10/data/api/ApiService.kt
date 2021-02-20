package app.googletop10.data.api

import app.googletop10.entity.responses.SearchResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
  @GET("customsearch/v1")
  fun search(
      @Query("key") key: String,
      @Query("cx") cx: String,
      @Query("q") query: String
  ): Flowable<SearchResponse>
}
