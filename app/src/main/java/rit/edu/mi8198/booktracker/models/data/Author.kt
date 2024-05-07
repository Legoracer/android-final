package rit.edu.mi8198.booktracker.models.data


import com.google.gson.annotations.SerializedName

data class Author(
    @SerializedName("alternate_names")
    val alternateNames: List<String>,
    @SerializedName("bio")
    val bio: String,
    @SerializedName("birth_date")
    val birthDate: String,
    @SerializedName("created")
    val created: Created,
    @SerializedName("entity_type")
    val entityType: String,
    @SerializedName("fuller_name")
    val fullerName: String,
    @SerializedName("key")
    val key: String,
    @SerializedName("last_modified")
    val lastModified: LastModified,
    @SerializedName("latest_revision")
    val latestRevision: Int,
    @SerializedName("links")
    val links: List<Link>,
    @SerializedName("name")
    val name: String,
    @SerializedName("personal_name")
    val personalName: String,
    @SerializedName("photos")
    val photos: List<Int>,
    @SerializedName("remote_ids")
    val remoteIds: RemoteIds,
    @SerializedName("revision")
    val revision: Int,
    @SerializedName("source_records")
    val sourceRecords: List<String>,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: Type,
    @SerializedName("wikipedia")
    val wikipedia: String
) {
    data class Created(
        @SerializedName("type")
        val type: String,
        @SerializedName("value")
        val value: String
    )

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

    data class RemoteIds(
        @SerializedName("amazon")
        val amazon: String,
        @SerializedName("goodreads")
        val goodreads: String,
        @SerializedName("isni")
        val isni: String,
        @SerializedName("librarything")
        val librarything: String,
        @SerializedName("storygraph")
        val storygraph: String,
        @SerializedName("viaf")
        val viaf: String,
        @SerializedName("wikidata")
        val wikidata: String
    )

    data class Type(
        @SerializedName("key")
        val key: String
    )
}