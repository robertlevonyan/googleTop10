package app.googletop10.presentation.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import app.googletop10.entity.responses.SearchItem
import app.googletop10.presentation.ui.component.SearchAdapter

@BindingAdapter("search_adapter")
fun RecyclerView.setSearchAdapter(data: List<SearchItem>?) {
    println("SEARCH 1 ${data?.toTypedArray()?.contentToString() ?: "null"}")
    if (data == null) return
    println("SEARCH 2 ${data.toTypedArray().contentToString()}")
    adapter = SearchAdapter().apply {
        submitList(data)
    }
}
