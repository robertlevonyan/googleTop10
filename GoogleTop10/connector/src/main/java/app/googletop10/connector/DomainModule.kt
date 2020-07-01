package app.googletop10.connector

import app.googletop10.domain.usecase.SearchUseCase
import app.googletop10.domain.usecase.SearchUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    single<SearchUseCase> { SearchUseCaseImpl(get()) }
}
