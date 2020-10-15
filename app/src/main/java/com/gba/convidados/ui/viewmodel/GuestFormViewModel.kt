package com.gba.convidados.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gba.convidados.model.Guest
import com.gba.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext

    private val repository: GuestRepository = GuestRepository.getInstance(context)

    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest : LiveData<Boolean> = mSaveGuest

    fun save(guest: Guest) {
        mSaveGuest.value = repository.save(guest)
    }
}