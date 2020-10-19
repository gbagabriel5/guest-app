package com.gba.convidados.helper

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gba.convidados.dao.GuestDAO
import com.gba.convidados.model.Guest

@Database(entities = [Guest::class], version = 1)
abstract class GuestDataBase : RoomDatabase() {

    abstract fun guestDao() : GuestDAO

    companion object {

        private lateinit var INSTANCE : GuestDataBase

        fun getDataBase(context: Context) : GuestDataBase {
            if(!::INSTANCE.isInitialized) {
                synchronized(GuestDataBase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        GuestDataBase::class.java,
                        "guestDB"
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }
}