package com.gba.convidados.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.gba.convidados.constants.DataBaseConstants

class GuestDataBase(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_GUEST)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    companion object {
        private const val DATABASE_NAME = "guest.db"
        private const val DATABASE_VERSION = 1

        private const val CREATE_TABLE_GUEST =
            ("CREATE TABLE " + DataBaseConstants.GUEST.TABLE_NAME + " ("
                    + DataBaseConstants.GUEST.COLUMNS.ID + " INTEGER AUTO_INCREMENT PRIMARY KEY, "
                    + DataBaseConstants.GUEST.COLUMNS.NAME + " TEXT, "
                    + DataBaseConstants.GUEST.COLUMNS.PRESENCE + " INTEGER);"
                    )
    }
}