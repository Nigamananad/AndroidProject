package com.example.recyclerapplication.sqlite_task_like_dislike.model

import android.annotation.SuppressLint
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

        // Define table and column names
        const val TABLE_NAME = "items"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "seriesNo"
        private const val COLUMN_LIKE = "like_Status"
        private const val COLUMN_DISLIKE = "dis_Like_Status"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME " +
                "($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NAME TEXT, " +
                "$COLUMN_LIKE INTEGER, " +
                "$COLUMN_DISLIKE INTEGER )"
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertItem(name: String, likeStatus: Int, disLikeStatus: Int): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_LIKE, likeStatus)
        values.put(COLUMN_DISLIKE, disLikeStatus)
        val id = db.insert(TABLE_NAME, null, values)
//        db.close()
        return id
    }

    @SuppressLint("Range")
    fun getAllItems(): ArrayList<SeriesNo> {
        val itemList = ArrayList<SeriesNo>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor: Cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                val likeStatus = cursor.getInt(cursor.getColumnIndex(COLUMN_LIKE))
                val disLikeStatus = cursor.getInt(cursor.getColumnIndex(COLUMN_DISLIKE))
                itemList.add(SeriesNo(id, name, likeStatus, disLikeStatus))
            } while (cursor.moveToNext())
        }
        cursor.close()
//        db.close()
        return itemList
    }

    fun clearTable(tableName: String) {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $tableName")
//        db.close()
    }

    fun likeItem(likeStatus: Int, itemId: Int) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_LIKE, likeStatus) // Set like status to 1
        db.update(TABLE_NAME, values, "$COLUMN_ID=?", arrayOf(itemId.toString()))
        db.close()
    }

    fun dislikeItem( disLike:Int,itemId: Int) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_DISLIKE, disLike) // Set like status to -1
        db.update(TABLE_NAME, values, "$COLUMN_ID=?", arrayOf(itemId.toString()))
        db.close()
    }

    @SuppressLint("Range")
    fun getDislikedItems(): List<SeriesNo> {
        val dislikedItemList = mutableListOf<SeriesNo>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_DISLIKE = 1"
        val cursor: Cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                val likeStatus = cursor.getInt(cursor.getColumnIndex(COLUMN_LIKE))
                val dislikeStatus = cursor.getInt(cursor.getColumnIndex(COLUMN_DISLIKE))
                dislikedItemList.add(SeriesNo(id.toInt(), name, likeStatus, dislikeStatus))
            } while (cursor.moveToNext())
        }
        cursor.close()
//        db.close()
        return dislikedItemList
    }

    @SuppressLint("Range")
    fun getNewLikedItems(): List<SeriesNo> {
        val likedItemList = mutableListOf<SeriesNo>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_LIKE = 1"
        val cursor: Cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                val likeStatus = cursor.getInt(cursor.getColumnIndex(COLUMN_LIKE))
                val dislikeStatus = cursor.getInt(cursor.getColumnIndex(COLUMN_DISLIKE))
                likedItemList.add(SeriesNo(id.toInt(), name, likeStatus, dislikeStatus))
            } while (cursor.moveToNext())
        }
        cursor.close()
//        db.close()
        return likedItemList
    }
}