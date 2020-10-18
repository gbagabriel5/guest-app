package com.gba.convidados.ui.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gba.convidados.R
import com.gba.convidados.model.Guest
import com.gba.convidados.ui.listener.GuestListener
import kotlinx.android.synthetic.main.row_guest.view.*

class GuestAllAdapter : RecyclerView.Adapter<GuestAllAdapter.GuestViewHolder>() {

    private var guestList: List<Guest> = arrayListOf()
    private lateinit var mListener: GuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_guest, parent,false)
        return GuestViewHolder(item, mListener)
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

    fun attachListener(listener: GuestListener) {
        mListener = listener
    }

    class GuestViewHolder(itemView: View,
                          private var listener: GuestListener) : RecyclerView.ViewHolder(itemView) {

        fun bind(guest: Guest) {
            itemView.txtName.text = guest.name
            itemView.txtName.setOnClickListener {
                listener.onClick(guest.id)
            }
            itemView.txtName.setOnLongClickListener {
                AlertDialog.Builder(itemView.context)
                    .setTitle(R.string.remocao_convidado)
                    .setMessage(R.string.deseja_remover)
                    .setPositiveButton(R.string.remover) { dialog, wich ->
                        listener.onDelete(guest.id)
                    }
                    .setNeutralButton(R.string.cancelar,null)
                    .show()
                true
            }
        }
    }
}
