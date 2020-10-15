package com.gba.convidados.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gba.convidados.R
import com.gba.convidados.model.Guest
import com.gba.convidados.ui.viewmodel.GuestFormViewModel
import kotlinx.android.synthetic.main.activity_guest_form.*

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
        observe()
    }

    override fun onClick(v: View) {
        val id = v.id
        if(id == R.id.btnSave) {
            val name = editNome.text.toString()
            val presence = radio_presente.isChecked
            viewModel.save(Guest(name = name, presence = presence))
        }
    }

    private fun setListeners() = btnSave.setOnClickListener(this)

    private fun observe() {
        viewModel.saveGuest.observe(this, Observer {
            if(it)
                Toast.makeText(applicationContext, "Sucesso!", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(applicationContext, "Falha!", Toast.LENGTH_SHORT).show()
            finish()
        })
    }
}
