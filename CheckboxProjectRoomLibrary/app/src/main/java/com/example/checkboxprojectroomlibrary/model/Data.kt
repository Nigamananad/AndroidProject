package com.example.checkboxprojectroomlibrary.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "selected_icons")
data class Data(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var name:String,
    var iconResourceId: String
)
