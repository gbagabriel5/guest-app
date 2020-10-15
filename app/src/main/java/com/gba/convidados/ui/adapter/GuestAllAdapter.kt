package com.gba.convidados.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gba.convidados.R
import com.gba.convidados.model.Guest
import kotlinx.android.synthetic.main.row_guest.view.*

class GuestAllAdapter : RecyclerView.Adapter<GuestAllAdapter.GuestViewHolder>() {

    private var guestList: List<Guest> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_guest, parent,false)
        return GuestViewHolder(item)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(guestList[position])
    }

    override fun getItemCount(): Int {
        return guestList.count()
    }

    fun updateGuests(list: List<Guest>) {
        guestList = list
        notifyDataSetChanged()

    }

    class GuestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(guest: Guest) {
            itemView.txtName.text = guest.name

        }
    }
}
