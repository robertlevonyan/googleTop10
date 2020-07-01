package app.googletop10.data

import android.content.Context
import android.os.Build
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.googletop10.data.db.AppDatabase
import app.googletop10.data.db.SearchDao
import app.googletop10.entity.responses.SearchItem
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class DbUnitTest {
    private lateinit var searchDao: SearchDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        searchDao = db.searchDao()
    }

    @Test
    @Throws(Exception::class)
    fun `test search input`() {
        val testSearchInput = SearchItem("Test", "some_url", "some_link", "something")
        searchDao.saveSearchResults(testSearchInput)
        val searchResults = searchDao.getSearchResults()

        assert(searchResults.isNotEmpty())
        assert(searchResults.size == 1)
        assert(searchResults[0] == testSearchInput)
    }

    @Test
    @Throws(Exception::class)
    fun `test search clear`() {
        val testSearchInput = SearchItem("Test", "some_url", "some_link", "something")
        searchDao.saveSearchResults(testSearchInput)
        searchDao.removeSearchResults()
        val searchResults = searchDao.getSearchResults()

        assert(searchResults.isEmpty())
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}