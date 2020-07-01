package app.googletop10.connector

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class DiUnitTest : KoinTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun `test modules`() = checkModules {
        androidContext(context)
        modules(dataModule)
        modules(domainModule)
        modules(presentationModule)
    }
}