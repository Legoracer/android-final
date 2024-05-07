package rit.edu.mi8198.booktracker.models.data


import com.google.gson.annotations.SerializedName

data class Trending(
    @SerializedName("days")
    val days: Int,
    @SerializedName("hours")
    val hours: Int,
    @SerializedName("query")
    val query: String,
    @SerializedName("works")
    val works: List<Work>
) {
    data class Work(
        @SerializedName("author_key")
        val authorKey: List<String>,
        @SerializedName("author_name")
        val authorName: List<String>,
        @SerializedName("availability")
        val availability: Availability?,
        @SerializedName("cover_edition_key")
        val coverEditionKey: String?,
        @SerializedName("cover_i")
        val coverI: Int,
        @SerializedName("edition_count")
        val editionCount: Int,
        @SerializedName("first_publish_year")
        val firstPublishYear: Int,
        @SerializedName("has_fulltext")
        val hasFulltext: Boolean,
        @SerializedName("ia")
        val ia: List<String>?,
        @SerializedName("ia_collection_s")
        val iaCollectionS: String?,
        @SerializedName("id_librivox")
        val idLibrivox: List<String>?,
        @SerializedName("id_project_gutenberg")
        val idProjectGutenberg: List<String>?,
        @SerializedName("id_standard_ebooks")
        val idStandardEbooks: List<String>?,
        @SerializedName("key")
        val key: String,
        @SerializedName("language")
        val language: List<String>?,
        @SerializedName("lending_edition_s")
        val lendingEditionS: String?,
        @SerializedName("lending_identifier_s")
        val lendingIdentifierS: String?,
        @SerializedName("public_scan_b")
        val publicScanB: Boolean,
        @SerializedName("subtitle")
        val subtitle: String?,
        @SerializedName("title")
        val title: String
    ) {
        data class Availability(
            @SerializedName("available_to_borrow")
            val availableToBorrow: Boolean?,
            @SerializedName("available_to_browse")
            val availableToBrowse: Boolean?,
            @SerializedName("available_to_waitlist")
            val availableToWaitlist: Boolean?,
            @SerializedName("identifier")
            val identifier: String,
            @SerializedName("is_browseable")
            val isBrowseable: Boolean?,
            @SerializedName("is_lendable")
            val isLendable: Boolean?,
            @SerializedName("is_previewable")
            val isPreviewable: Boolean,
            @SerializedName("is_printdisabled")
            val isPrintdisabled: Boolean?,
            @SerializedName("is_readable")
            val isReadable: Boolean?,
            @SerializedName("is_restricted")
            val isRestricted: Boolean,
            @SerializedName("isbn")
            val isbn: String?,
            @SerializedName("last_loan_date")
            val lastLoanDate: String?,
            @SerializedName("last_waitlist_date")
            val lastWaitlistDate: String?,
            @SerializedName("num_waitlist")
            val numWaitlist: String?,
            @SerializedName("oclc")
            val oclc: Any?,
            @SerializedName("openlibrary_edition")
            val openlibraryEdition: String?,
            @SerializedName("openlibrary_work")
            val openlibraryWork: String?,
            @SerializedName("__src__")
            val src: String,
            @SerializedName("status")
            val status: String
        )
    }
}