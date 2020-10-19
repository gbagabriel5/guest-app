package com.gba.convidados.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gba.convidados.R
import com.gba.convidados.constants.GuestConstants
import com.gba.convidados.ui.activity.GuestFormActivity
import com.gba.convidados.ui.adapter.GuestAllAdapter
import com.gba.convidados.ui.listener.GuestListener
import com.gba.convidados.ui.viewmodel.AllGuestsViewModel
import kotlinx.android.synthetic.main.fragment_absent.*
import kotlinx.android.synthetic.main.fragment_all.*

class AbsentsFragment : Fragment() {

    private lateinit var viewModel: AllGuestsViewModel
    private val guestAdapter: GuestAllAdapter = GuestAllAdapter()
    private lateinit var mListener: GuestListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(AllGuestsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_absent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerAbsent.layoutManager = LinearLayoutManager(context)
        recyclerAbsent.adapter = guestAdapter

        mListener = object : GuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUESTID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.load(GuestConstants.FILTER.ABSENT)
            }
        }
        guestAdapter.attachListener(mListener)
        observe()
    }

    override fun onResume() {
        super.onResume()
        viewModel.load(GuestConstants.FILTER.ABSENT)
    }

    private fun observe() {
        viewModel.guestList.observe(viewLifecycleOwner, Observer {
            guestAdapter.updateGuests(it)
        })
    }
}