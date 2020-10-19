package com.gba.convidados.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gba.convidados.model.Guest
import com.gba.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext

    private val repository: GuestRepository = GuestRepository(context)

    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest : LiveData<Boolean> = mSaveGuest

    private var mGuest = MutableLiveData<Guest>()
    val guest : LiveData<Guest> = mGuest

    fun save(guest: Guest) {
        if (guest.id == 0)
            mSaveGuest.value = repository.save(guest)
        else
            mSaveGuest.value = repository.update(guest)
    }

    fun load(id: Int) {
        mGuest.value = repository.get(id)
    }
}