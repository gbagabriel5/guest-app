package com.gba.convidados.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gba.convidados.R
import com.gba.convidados.ui.adapter.GuestAllAdapter
import com.gba.convidados.ui.viewmodel.AllGuestsViewModel
import kotlinx.android.synthetic.main.fragment_all.*

class AllGuestsFragment : Fragment() {

    private lateinit var allGuestsViewModel: AllGuestsViewModel

    private val guestAdapter: GuestAllAdapter = GuestAllAdapter()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        allGuestsViewModel = ViewModelProvider(this).get(AllGuestsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_all, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerAll.layoutManager = LinearLayoutManager(context)
        recyclerAll.adapter = guestAdapter

        observe()
    }

    override fun onResume() {
        super.onResume()
        allGuestsViewModel.load()
    }

    private fun observe() {
        allGuestsViewModel.guestList.observe(viewLifecycleOwner, Observer {
            guestAdapter.updateGuests(it)
        })
    }
}