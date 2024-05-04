package rit.edu.mi8198.booktracker.models.data


import com.google.gson.annotations.SerializedName

data class BookWhichIOnlyNeedForPageCount(
    @SerializedName("authors")
    val authors: List<Author>,
    @SerializedName("contributors")
    val contributors: List<Contributor>,
    @SerializedName("copyright_date")
    val copyrightDate: String,
    @SerializedName("covers")
    val covers: List<Int>,
    @SerializedName("created")
    val created: Created,
    @SerializedName("isbn_10")
    val isbn10: List<String>,
    @SerializedName("isbn_13")
    val isbn13: List<String>,
    @SerializedName("key")
    val key: String,
    @SerializedName("languages")
    val languages: List<Language>,
    @SerializedName("last_modified")
    val lastModified: LastModified,
    @SerializedName("latest_revision")
    val latestRevision: Int,
    @SerializedName("notes")
    val notes: Notes,
    @SerializedName("number_of_pages")
    val numberOfPages: Int,
    @SerializedName("ocaid")
    val ocaid: String,
    @SerializedName("oclc_numbers")
    val oclcNumbers: List<String>,
    @SerializedName("physical_format")
    val physicalFormat: String,
    @SerializedName("publish_date")
    val publishDate: String,
    @SerializedName("publish_places")
    val publishPlaces: List<String>,
    @SerializedName("publishers")
    val publishers: List<String>,
    @SerializedName("revision")
    val revision: Int,
    @SerializedName("source_records")
    val sourceRecords: List<String>,
    @SerializedName("title")
    val title: String,
    @SerializedName("translation_of")
    val translationOf: String,
    @SerializedName("type")
    val type: Type,
    @SerializedName("works")
    val works: List<Work>
) {
    data class Author(
        @SerializedName("key")
        val key: String
    )

    data class Contributor(
        @SerializedName("name")
        val name: String,
        @SerializedName("role")
        val role: String
    )

    data class Created(
        @SerializedName("type")
        val type: String,
        @SerializedName("value")
        val value: String
    )

    data class Language(
        @SerializedName("key")
        val key: String
    )

    data class LastModified(
        @SerializedName("type")
        val type: String,
        @SerializedName("value")
        val value: String
    )

    data class Notes(
        @SerializedName("type")
        val type: String,
        @SerializedName("value")
        val value: String
    )

    data class Type(
        @SerializedName("key")
        val key: String
    )

    data class Work(
        @SerializedName("key")
        val key: String
    )
}