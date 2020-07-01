package app.googletop10.connector

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun initKoin(context: Context) {
  startKoin {
    androidContext(context)
    modules(dataModule, domainModule, presentationModule)
  }
}