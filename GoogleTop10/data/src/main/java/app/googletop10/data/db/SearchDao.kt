package app.googletop10.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import app.googletop10.entity.responses.SearchItem

@Dao
interface SearchDao {
    @Insert
    fun saveSearchResults(vararg searchItems: SearchItem)

    @Query("SELECT * FROM SearchItem")
    fun getSearchResults(): List<SearchItem>

    @Query("DELETE FROM SearchItem")
    fun removeSearchResults()
}
