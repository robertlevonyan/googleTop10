package app.googletop10.connector

import app.googletop10.presentation.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { SearchViewModel(get()) }
}
