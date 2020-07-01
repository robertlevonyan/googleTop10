package app.googletop10

import android.app.Application
import app.googletop10.connector.initKoin

class App : Application() {

  override fun onCreate() {
    super.onCreate()
    initKoin(this)
  }
}
