package com.peter.pretest.data.source.local

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import androidx.room.TypeConverter
import com.peter.pretest.data.Source
import java.lang.Exception


class PretestConverter {

    /**
     * Convert Json to [List] [String]
     */
    @TypeConverter
    fun convertJsonToList(json: String?): List<Source>? {
        json?.let {
            val type = Types.newParameterizedType(List::class.java, Source::class.java)
            val adapter: JsonAdapter<List<Source>> = Moshi.Builder().build().adapter(type)
            return adapter.fromJson(it)
        }
        return null
    }

}