import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@Serializable
data class Article (
    val id: String,
    val title: String,
    val content: String,
    val image: String,

    @SerialName("published_on")
    val publishedOn: String,

    @SerialName("category_list")
    val categoryList: List<String>,

    val relevancy: String,

    @SerialName("source_url")
    val sourceURL: String
)
