package com.example.tugas4

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context): SQLiteOpenHelper(context, "Userdata", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table Userdata(Username TEXT, Userno TEXT primary key)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("drop table if exists Userdata")
    }

    fun saveuserdata(Username: String, Userno: String): Boolean {
        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("Username", Username)
        cv.put("Userno", Userno)
        val result = p0.insert("Userdata", null, cv)
        if (result == -1.toLong()) {
            return false
        }
        return true
    }

    fun gettext(): Cursor {
        val db = this.readableDatabase
        // Mengembalikan cursor dengan query, tidak nullable
        return db.rawQuery("SELECT * FROM Userdata", null) ?: throw IllegalStateException("Database query failed")
    }

}