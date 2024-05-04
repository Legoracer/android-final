package rit.edu.mi8198.booktracker.models

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rit.edu.mi8198.booktracker.models.data.Book
import rit.edu.mi8198.booktracker.models.data.BookWhichIOnlyNeedForPageCount
import rit.edu.mi8198.booktracker.models.data.Search


const val BASE_URL = "https://openlibrary.org/"
public interface OpenLibraryAPI {
    @GET("books/{id}.json/")
    suspend fun getBook(@Path("id") bookId: String): Book

    @GET("books/{id}.json/")
    suspend fun getBookAnother(@Path("id") bookId: String): BookWhichIOnlyNeedForPageCount

    @GET("search.json?/")
    suspend fun search(
        @Query("q") search: String,
        @Query("limit") limit: Int
    ): Search

    companion object {
        private val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        private val client: OkHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
        }.build()

        var api: OpenLibraryAPI? = null

        fun getInstance(): OpenLibraryAPI {
            if(api == null) {
                api = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build().create(OpenLibraryAPI::class.java)
            }
            return api!!
        }
    }
}