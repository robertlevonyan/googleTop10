package app.googletop10.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import app.googletop10.entity.responses.SearchItem
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface SearchDao {
  @Insert
  fun saveSearchResults(vararg searchItems: SearchItem): Completable

  @Query("SELECT * FROM SearchItem")
  fun getSearchResults(): Flowable<List<SearchItem>>

  @Query("DELETE FROM SearchItem")
  fun removeSearchResults(): Single<Int>
}
