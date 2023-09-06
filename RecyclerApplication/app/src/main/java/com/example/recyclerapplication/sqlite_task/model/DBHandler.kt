package com.example.recyclerapplication.sqlite_task.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_NAME = "coursedb"
        private const val DB_VERSION = 1
        private const val TABLE_NAME = "mycourses"
        private const val ID_COL = "id"
        private const val NAME_COL = "name"
        private const val DURATION_COL = "duration"
        private const val DESCRIPTION_COL = "description"
        private const val DATE = "date"
        private const val TRACKS_COL = "tracks"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val query = "CREATE TABLE $TABLE_NAME (" +
                "$ID_COL INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$NAME_COL TEXT, " +
                "$DURATION_COL TEXT, " +
                "$DESCRIPTION_COL TEXT, " +
                "$DATE TEXT," +
                "$TRACKS_COL TEXT)"
        db.execSQL(query)
    }

    fun addNewCourse(
        courseName: String,
        courseDuration: String,
        courseDescription: String,
        courseDAte: String,
        courseTracks: String
    ) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(NAME_COL, courseName)
        values.put(DURATION_COL, courseDuration)
        values.put(DESCRIPTION_COL, courseDescription)
        values.put(DATE, courseDAte)
        values.put(TRACKS_COL, courseTracks)
        db.insert(TABLE_NAME, null, values)
    }

    fun readCourses(): ArrayList<CourseModal> {
        val db = readableDatabase
        val cursorCourses = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val courseModalArrayList = ArrayList<CourseModal>()

        if (cursorCourses.moveToFirst()) {
            do {
                courseModalArrayList.add(
                    CourseModal(
                        cursorCourses.getString(0),
                        cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3),
                        cursorCourses.getString(4),
                        cursorCourses.getString(5)
                    )
                )
            } while (cursorCourses.moveToNext())
        }
        cursorCourses.close()
        return courseModalArrayList
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }


    fun deleteCourse(courseId: String): Boolean {
        try {
            val db = writableDatabase
            val whereClause = "$ID_COL = ?"
            val whereArgs = arrayOf(courseId.toString())
            val deleted = db.delete(TABLE_NAME, whereClause, whereArgs)
            db.close()
            Log.d("DeleteCourse", "Deleted: $deleted")
            return deleted > 0
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("DeleteCourse", "Delete failed: ${e.message}")
            return false
        }
    }

}