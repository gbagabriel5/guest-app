package com.gba.convidados.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gba.convidados.constants.GuestConstants
import com.gba.convidados.model.Guest
import com.gba.convidados.repository.GuestRepository

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository(application.applicationContext)

    private val mGuestList = MutableLiveData<List<Guest>>()
    val guestList: LiveData<List<Guest>> = mGuestList

    fun load(filter: Int) {
        when (filter) {
            GuestConstants.FILTER.EMPTY -> {
                mGuestList.value = repository.getAll()
            }
            GuestConstants.FILTER.PRESENT -> {
                mGuestList.value = repository.getPresent()
            }
            GuestConstants.FILTER.ABSENT -> {
                mGuestList.value = repository.getAbsent()
            }
        }
    }

    fun delete(id: Int) {
        val guest = repository.get(id)
        repository.delete(guest)
    }
}