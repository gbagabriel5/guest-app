package com.gba.convidados.repository

import android.content.Context
import com.gba.convidados.helper.GuestDataBase
import com.gba.convidados.model.Guest

class GuestRepository(context: Context) {

    private val dataBase = GuestDataBase.getDataBase(context).guestDao()

    fun save(guest: Guest) : Boolean = dataBase.save(guest) > 0

    fun update(guest: Guest) : Boolean = dataBase.update(guest) > 0

    fun delete(guest: Guest) = dataBase.delete(guest)

    fun get(id: Int) : Guest = dataBase.get(id)

    fun getAll(): List<Guest> = dataBase.getAll()

    fun getPresent(): List<Guest> = dataBase.getPresent()

    fun getAbsent(): List<Guest> = dataBase.getAbsent()
}