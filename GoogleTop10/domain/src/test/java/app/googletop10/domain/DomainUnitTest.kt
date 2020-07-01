package app.googletop10.domain

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import app.googletop10.connector.dataModule
import app.googletop10.connector.domainModule
import app.googletop10.connector.presentationModule
import app.googletop10.domain.usecase.SearchUseCase
import app.googletop10.entity.ActionResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class DomainUnitTest : KoinTest {

    private val searchUseCase: SearchUseCase by inject()

    @Before
    fun init() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        startKoin {
            androidContext(context)
            modules(dataModule, domainModule, presentationModule)
        }
    }

    @Test
    fun `test search empty`() = runBlocking {
        searchUseCase.search("").let {
            when (it) {
                is ActionResult.Success -> {
                    assert(it.data.isEmpty())
                }
                is ActionResult.Error -> {
                    assert(false)
                }
            }
        }
    }

    @Test
    fun `test search`() = runBlocking(Dispatchers.IO) {
        searchUseCase.search("test").let {
            when (it) {
                is ActionResult.Success -> {
                    assert(it.data.isNotEmpty())
                    assert(it.data.size == 10)
                    val results = searchUseCase.getSavedSearches()
                    assert(results.isNotEmpty())
                }
                is ActionResult.Error -> {
                    assert(false)
                }
            }
        }
    }

    @Test
    fun `test saved searches`() = runBlocking(Dispatchers.IO) {
        val results = searchUseCase.getSavedSearches()

        assert(results.isEmpty())
    }
}
