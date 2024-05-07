package rit.edu.mi8198.booktracker.models

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import rit.edu.mi8198.booktracker.models.data.Book

class Converter {
    // Author List
    class AuthorListConverter {
        private val gson = Gson()

        @TypeConverter
        fun fromAuthorList(authors: List<Book.Author>?): String? {
            return gson.toJson(authors)
        }

        @TypeConverter
        fun toAuthorList(authorString: String?): List<Book.Author>? {
            if (authorString == null) return null
            val type = object : TypeToken<List<Book.Author>>() {}.type
            return gson.fromJson(authorString, type)
        }
    }

    // String List
    class StringListConverter {
        private val gson = Gson()

        @TypeConverter
        fun fromStringList(strings: List<String>?): String? {
            return gson.toJson(strings)
        }

        @TypeConverter
        fun toStringList(string: String?): List<String>? {
            if (string == null) return null
            val type = object : TypeToken<List<String>>() {}.type
            return gson.fromJson(string, type)
        }
    }

    // Int List
    class IntListConverter {
        private val gson = Gson()

        @TypeConverter
        fun fromIntList(ints: List<Int>?): String? {
            return gson.toJson(ints)
        }

        @TypeConverter
        fun toIntList(intString: String?): List<Int>? {
            if (intString == null) return null
            val type = object : TypeToken<List<Int>>() {}.type
            return gson.fromJson(intString, type)
        }
    }

    // JSON
    class JsonElementConverter {
        private val gson = Gson()

        @TypeConverter
        fun fromJsonElement(json: JsonElement?): String? {
            return gson.toJson(json)
        }

        @TypeConverter
        fun toJsonElement(data: String?): JsonElement? {
            return if (data.isNullOrEmpty()) null else gson.fromJson(data, JsonElement::class.java)
        }
    }
}