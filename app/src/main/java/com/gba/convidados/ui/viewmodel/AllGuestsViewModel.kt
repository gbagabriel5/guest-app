package com.gba.convidados.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gba.convidados.model.Guest
import com.gba.convidados.repository.GuestRepository

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository.getInstance(application.applicationContext)

    private val mGuestList = MutableLiveData<List<Guest>>()
    val guestList: LiveData<List<Guest>> = mGuestList

    fun load() {
        mGuestList.value = repository.getAll()
    }

    fun delete(id: Int) {
        repository.delete(id)
    }
}