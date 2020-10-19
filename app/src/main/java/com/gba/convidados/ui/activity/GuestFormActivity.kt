package com.gba.convidados.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gba.convidados.R
import com.gba.convidados.constants.GuestConstants
import com.gba.convidados.model.Guest
import com.gba.convidados.ui.viewmodel.GuestFormViewModel
import kotlinx.android.synthetic.main.activity_guest_form.*

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewModel: GuestFormViewModel

    private var mGuestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
        observe()
        loadData()

        radio_present.isChecked = true
    }

    override fun onClick(v: View) {
        val id = v.id
        if(id == R.id.btnSave) {
            val name = editName.text.toString()
            val presence = radio_present.isChecked
            val guest = Guest().apply {
                this.id = mGuestId
                this.name = name
                this.presence = presence
            }
            viewModel.save(guest)
        }
    }

    private fun setListeners() = btnSave.setOnClickListener(this)

    private fun loadData() {
        val bundle = intent.extras
        bundle?.let {
            mGuestId = bundle.getInt(GuestConstants.GUESTID)
            viewModel.load(mGuestId)
            btnSave.text = "ATUALIZAR"
        }
    }

    private fun observe() {
        viewModel.saveGuest.observe(this, Observer {
            if(it)
                Toast.makeText(applicationContext, "Sucesso!", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(applicationContext, "Falha!", Toast.LENGTH_SHORT).show()
            finish()
        })
        viewModel.guest.observe(this, Observer {
            editName.setText(it.name)
            if (it.presence)
                radio_present.isChecked = true
            else
                radio_absent.isChecked = true
        })
    }
}
