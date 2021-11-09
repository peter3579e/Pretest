package com.peter.pretest.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "source_table", primaryKeys = ["currency_id"])
@Parcelize
data class Source(
 @ColumnInfo(name = "currency_id")
 @Json(name = "id")var id: String,
 @ColumnInfo(name = "currency_name")
 @Json(name = "name")var name: String,
 @ColumnInfo(name = "currency_symbol")
 @Json(name = "symbol")var symbol: String
) : Parcelable