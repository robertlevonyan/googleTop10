package app.googletop10.connector

import app.googletop10.data.api.ApiService
import app.googletop10.data.api.RetrofitClient
import app.googletop10.data.db.AppDatabase
import app.googletop10.data.repository.SearchRepositoryImpl
import app.googletop10.domain.repository.SearchRepository
import org.koin.dsl.module

val dataModule = module {
    single { AppDatabase.getInstance(get()) }

    single { get<AppDatabase>().searchDao() }

    single<ApiService> { RetrofitClient.getClient().create(ApiService::class.java) }

    single<SearchRepository> { SearchRepositoryImpl(get(), get()) }
}
