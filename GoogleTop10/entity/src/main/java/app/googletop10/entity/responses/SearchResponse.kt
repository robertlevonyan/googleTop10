package app.googletop10.entity.responses

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("items")
    val searchItems: List<SearchItem>
)

@Entity
data class SearchItem(
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("displayLink")
    val displayLink: String,
    @SerializedName("snippet")
    val snippet: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}