package app.googletop10.presentation.ui.component

import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.googletop10.entity.responses.SearchItem
import app.googletop10.presentation.R
import app.googletop10.presentation.databinding.ItemSearchBinding
import app.googletop10.presentation.util.inflate

class SearchAdapter :
    ListAdapter<SearchItem, SearchAdapter.SearchViewHolder>(SearchItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
        SearchViewHolder(parent inflate R.layout.item_search)

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SearchViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SearchItem) {
            binding.item = item
            binding.executePendingBindings()

            itemView.setOnClickListener {
                CustomTabsIntent.Builder().build().launchUrl(itemView.context, item.link.toUri())
            }
        }
    }
}
