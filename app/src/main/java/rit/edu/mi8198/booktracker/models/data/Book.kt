package rit.edu.mi8198.booktracker.models.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.google.gson.JsonElement
import rit.edu.mi8198.booktracker.models.Converter

@Entity(tableName = "book")
@TypeConverters(Converter.JsonElementConverter::class, Converter.AuthorListConverter::class, Converter.StringListConverter::class, Converter.IntListConverter::class)
data class Book(
    @PrimaryKey
    @SerializedName("key")
    @ColumnInfo(name = "key")
    val key: String,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String,

    @SerializedName("description")
    @TypeConverters(Converter.JsonElementConverter::class)
    @ColumnInfo(name = "description")
    val description: JsonElement?,

    @ColumnInfo(name = "currentPage")
    var currentPage: Int = 0,

    @SerializedName("authors")
    @TypeConverters(Converter.AuthorListConverter::class)
    @ColumnInfo(name = "authors")
    val authors: List<Author>,

    @SerializedName("subjects")
    @TypeConverters(Converter.StringListConverter::class)
    @ColumnInfo(name = "subjects")
    val subjects: List<String>,

    @SerializedName("covers")
    @TypeConverters(Converter.IntListConverter::class)
    @ColumnInfo(name = "covers")
    val covers: List<Int>,

    var isFavorite: Boolean?
) {
    val actualDescription: String
        get() = when {
            description == null -> ""
            description.isJsonPrimitive -> description.asString
            description.isJsonObject -> description.asJsonObject.get("value").asString
            else -> "No description available"
        }

    data class Author(
        @SerializedName("author")
        @ColumnInfo(name="author")
        val author: Author,
    ) {
        data class Author(
            @SerializedName("key")
            @ColumnInfo(name="key")
            val key: String
        )
    }
}