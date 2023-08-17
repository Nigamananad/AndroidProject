package com.example.recyclerapplication.model

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferenceUtil(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun saveDataList(dataList: List<YourDataItem>) {
        val editor = sharedPreferences.edit()
        val jsonString = Gson().toJson(dataList)
        editor.putString("dataList", jsonString)
        editor.apply()
    }

    fun getDataList(): List<YourDataItem> {
        val jsonString = sharedPreferences.getString("dataList", null)
        val type = object : TypeToken<List<YourDataItem>>() {}.type
        return Gson().fromJson(jsonString, type) ?: emptyList()
    }

}