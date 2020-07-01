package app.googletop10.presentation.ui.component

import androidx.recyclerview.widget.DiffUtil
import app.googletop10.entity.responses.SearchItem

class SearchItemCallback : DiffUtil.ItemCallback<SearchItem>() {
    override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean =
        oldItem == newItem
}