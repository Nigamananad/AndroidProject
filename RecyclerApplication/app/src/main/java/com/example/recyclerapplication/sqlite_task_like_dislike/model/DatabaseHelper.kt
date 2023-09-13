package com.example.recyclerapplication.sqlite_task_like_dislike.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "my_database"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "my_table"
        internal const val COLUMN_ID = "id"
        const val SERIES = "series_no"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery =
            "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $SERIES TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertData(series: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(SERIES, series)
        return db.insert(TABLE_NAME, null, contentValues)
    }

    fun getAllData(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    fun dataExists(): Boolean {
        val query = "SELECT COUNT(*) FROM $TABLE_NAME"
        val cursor = readableDatabase.rawQuery(query, null)
        cursor.moveToFirst()
        val count = cursor.getInt(0)
        cursor.close()
        return count > 0
    }

    fun insertStaticData() {
        if (!dataExists()) {
            // Insert your static data here
            val staticData = listOf("10001", "10002", "10003", "10004", "10005", "10006", "10007", "10008","10009","10010")

            for (series in staticData) {
                insertData(series)
            }
        }
    }
}