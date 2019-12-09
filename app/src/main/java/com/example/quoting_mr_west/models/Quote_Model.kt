package com.example.quoting_mr_west.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Quote_Model(
    @ColumnInfo(name = "quote")
    @SerializedName("quote")
    val quote: String?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}