package rit.edu.mi8198.booktracker.models.data


import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("authors")
    val authors: List<Author>,
    @SerializedName("covers")
    val covers: List<Int>,
    @SerializedName("created")
    val created: Created,
    @SerializedName("description")
    val description: Description,
    @SerializedName("excerpts")
    val excerpts: List<Excerpt>,
    @SerializedName("key")
    val key: String,
    @SerializedName("last_modified")
    val lastModified: LastModified,
    @SerializedName("latest_revision")
    val latestRevision: Int,
    @SerializedName("links")
    val links: List<Link>,
    @SerializedName("revision")
    val revision: Int,
    @SerializedName("subject_people")
    val subjectPeople: List<String>,
    @SerializedName("subject_places")
    val subjectPlaces: List<String>,
    @SerializedName("subjects")
    val subjects: List<String>,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: Type
) {
    data class Author(
        @SerializedName("author")
        val author: Author,
        @SerializedName("type")
        val type: Type
    ) {
        data class Author(
            @SerializedName("key")
            val key: String
        )

        data class Type(
            @SerializedName("key")
            val key: String
        )
    }

    data class Created(
        @SerializedName("type")
        val type: String,
        @SerializedName("value")
        val value: String
    )

    data class Description(
        @SerializedName("type")
        val type: String,
        @SerializedName("value")
        val value: String
    )

    data class Excerpt(
        @SerializedName("author")
        val author: Author,
        @SerializedName("comment")
        val comment: String,
        @SerializedName("excerpt")
        val excerpt: String
    ) {
        data class Author(
            @SerializedName("key")
            val key: String
        )
    }

    data class LastModified(
        @SerializedName("type")
        val type: String,
        @SerializedName("value")
        val value: String
    )

    data class Link(
        @SerializedName("title")
        val title: String,
        @SerializedName("type")
        val type: Type,
        @SerializedName("url")
        val url: String
    ) {
        data class Type(
            @SerializedName("key")
            val key: String
        )
    }

    data class Type(
        @SerializedName("key")
        val key: String
    )
}