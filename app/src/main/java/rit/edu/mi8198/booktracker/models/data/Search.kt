package rit.edu.mi8198.booktracker.models.data


import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("docs")
    val books: List<Book>,
    @SerializedName("numFound")
    val numFound: Int,
    @SerializedName("num_found")
    val numFound2: Int,
    @SerializedName("numFoundExact")
    val numFoundExact: Boolean,
    @SerializedName("offset")
    val offset: Any,
    @SerializedName("q")
    val q: String,
    @SerializedName("start")
    val start: Int
) {
    data class Book(
        @SerializedName("already_read_count")
        val alreadyReadCount: Int,
        @SerializedName("author_alternative_name")
        val authorAlternativeName: List<String>,
        @SerializedName("author_facet")
        val authorFacet: List<String>,
        @SerializedName("author_key")
        val authorKey: List<String>,
        @SerializedName("author_name")
        val authorName: List<String>,
        @SerializedName("contributor")
        val contributor: List<String>,
        @SerializedName("cover_edition_key")
        val coverEditionKey: String,
        @SerializedName("cover_i")
        val coverImageID: Int,
        @SerializedName("currently_reading_count")
        val currentlyReadingCount: Int,
        @SerializedName("ddc")
        val ddc: List<String>,
        @SerializedName("ddc_sort")
        val ddcSort: String,
        @SerializedName("ebook_access")
        val ebookAccess: String,
        @SerializedName("ebook_count_i")
        val ebookCountI: Int,
        @SerializedName("edition_count")
        val editionCount: Int,
        @SerializedName("edition_key")
        val editionKey: List<String>,
        @SerializedName("first_publish_year")
        val firstPublishYear: Int,
        @SerializedName("first_sentence")
        val firstSentence: List<String>,
        @SerializedName("format")
        val format: List<String>,
        @SerializedName("has_fulltext")
        val hasFulltext: Boolean,
        @SerializedName("ia")
        val ia: List<String>,
        @SerializedName("ia_box_id")
        val iaBoxId: List<String>,
        @SerializedName("ia_collection")
        val iaCollection: List<String>,
        @SerializedName("ia_collection_s")
        val iaCollectionS: String,
        @SerializedName("ia_loaded_id")
        val iaLoadedId: List<String>,
        @SerializedName("id_abebooks_de")
        val idAbebooksDe: List<String>,
        @SerializedName("id_alibris_id")
        val idAlibrisId: List<String>,
        @SerializedName("id_amazon")
        val idAmazon: List<String>,
        @SerializedName("id_amazon_ca_asin")
        val idAmazonCaAsin: List<String>,
        @SerializedName("id_amazon_co_uk_asin")
        val idAmazonCoUkAsin: List<String>,
        @SerializedName("id_amazon_de_asin")
        val idAmazonDeAsin: List<String>,
        @SerializedName("id_amazon_it_asin")
        val idAmazonItAsin: List<String>,
        @SerializedName("id_better_world_books")
        val idBetterWorldBooks: List<String>,
        @SerializedName("id_bodleian__oxford_university")
        val idBodleianOxfordUniversity: List<String>,
        @SerializedName("id_british_library")
        val idBritishLibrary: List<String>,
        @SerializedName("id_british_national_bibliography")
        val idBritishNationalBibliography: List<String>,
        @SerializedName("id_canadian_national_library_archive")
        val idCanadianNationalLibraryArchive: List<String>,
        @SerializedName("id_depósito_legal")
        val idDepósitoLegal: List<String>,
        @SerializedName("id_goodreads")
        val idGoodreads: List<String>,
        @SerializedName("id_google")
        val idGoogle: List<String>,
        @SerializedName("id_hathi_trust")
        val idHathiTrust: List<String>,
        @SerializedName("id_librarything")
        val idLibrarything: List<String>,
        @SerializedName("id_overdrive")
        val idOverdrive: List<String>,
        @SerializedName("id_paperback_swap")
        val idPaperbackSwap: List<String>,
        @SerializedName("id_wikidata")
        val idWikidata: List<String>,
        @SerializedName("id_yakaboo")
        val idYakaboo: List<String>,
        @SerializedName("isbn")
        val isbn: List<String>,
        @SerializedName("key")
        val key: String,
        @SerializedName("language")
        val language: List<String>,
        @SerializedName("last_modified_i")
        val lastModifiedI: Int,
        @SerializedName("lcc")
        val lcc: List<String>,
        @SerializedName("lcc_sort")
        val lccSort: String,
        @SerializedName("lccn")
        val lccn: List<String>,
        @SerializedName("lending_edition_s")
        val lendingEditionS: String,
        @SerializedName("lending_identifier_s")
        val lendingIdentifierS: String,
        @SerializedName("number_of_pages_median")
        val numberOfPagesMedian: Int,
        @SerializedName("oclc")
        val oclc: List<String>,
        @SerializedName("osp_count")
        val ospCount: Int,
        @SerializedName("person")
        val person: List<String>,
        @SerializedName("person_facet")
        val personFacet: List<String>,
        @SerializedName("person_key")
        val personKey: List<String>,
        @SerializedName("place")
        val place: List<String>,
        @SerializedName("place_facet")
        val placeFacet: List<String>,
        @SerializedName("place_key")
        val placeKey: List<String>,
        @SerializedName("printdisabled_s")
        val printdisabledS: String,
        @SerializedName("public_scan_b")
        val publicScanB: Boolean,
        @SerializedName("publish_date")
        val publishDate: List<String>,
        @SerializedName("publish_place")
        val publishPlace: List<String>,
        @SerializedName("publish_year")
        val publishYear: List<Int>,
        @SerializedName("publisher")
        val publisher: List<String>,
        @SerializedName("publisher_facet")
        val publisherFacet: List<String>,
        @SerializedName("ratings_average")
        val ratingsAverage: Double,
        @SerializedName("ratings_count")
        val ratingsCount: Int,
        @SerializedName("ratings_count_1")
        val ratingsCount1: Int,
        @SerializedName("ratings_count_2")
        val ratingsCount2: Int,
        @SerializedName("ratings_count_3")
        val ratingsCount3: Int,
        @SerializedName("ratings_count_4")
        val ratingsCount4: Int,
        @SerializedName("ratings_count_5")
        val ratingsCount5: Int,
        @SerializedName("ratings_sortable")
        val ratingsSortable: Double,
        @SerializedName("readinglog_count")
        val readinglogCount: Int,
        @SerializedName("seed")
        val seed: List<String>,
        @SerializedName("subject")
        val subject: List<String>,
        @SerializedName("subject_facet")
        val subjectFacet: List<String>,
        @SerializedName("subject_key")
        val subjectKey: List<String>,
        @SerializedName("subtitle")
        val subtitle: String,
        @SerializedName("time")
        val time: List<String>,
        @SerializedName("time_facet")
        val timeFacet: List<String>,
        @SerializedName("time_key")
        val timeKey: List<String>,
        @SerializedName("title")
        val title: String,
        @SerializedName("title_sort")
        val titleSort: String,
        @SerializedName("title_suggest")
        val titleSuggest: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("_version_")
        val version: Long,
        @SerializedName("want_to_read_count")
        val wantToReadCount: Int
    )
}