package com.example.yukigames.data.local

import androidx.room.TypeConverter
import com.example.yukigames.data.remote.dto.Genre
import com.example.yukigames.data.remote.dto.ParentPlatform
import com.example.yukigames.data.remote.dto.Platform
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromGenreList(value: List<Genre>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toGenreList(value: String): List<Genre>? {
        val listType = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromParentPlatformList(value: List<ParentPlatform>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toParentPlatformList(value: String): List<ParentPlatform>? {
        val listType = object : TypeToken<List<ParentPlatform>>() {}.type
        return Gson().fromJson(value, listType)
    }

}