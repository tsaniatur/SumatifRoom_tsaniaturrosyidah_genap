package com.example.sumatifroom_tsaniaturrosyidah_genap

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sumatifroom_tsaniaturrosyidah_genap.room.tbbarang
import kotlinx.android.synthetic.main.activity_edit.view.*
import kotlinx.android.synthetic.main.adapter_barang.view.*

class BarangAdapter (private val benda : ArrayList<tbbarang>, private val listener: OnAdapterListener)
    :RecyclerView.Adapter<BarangAdapter.tbbarangViewHolder>() {

    class tbbarangViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): tbbarangViewHolder {
        return tbbarangViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_barang, parent, false)
        )
    }

    override fun onBindViewHolder(holder: tbbarangViewHolder, position: Int) {
        val barangku = benda[position]
        holder.view.tvId.text = barangku.id.toString()
        holder.view.tvNama.text = barangku.nama
        holder.view.tvNama.setOnClickListener {
            listener.onClick(barangku)
        }
        holder.view.ivEdit.setOnClickListener {
            listener.onUpdate(barangku)
        }
        holder.view.ivDelete.setOnClickListener {
            listener.onDelete(barangku)
        }
    }
    override fun getItemCount() = benda.size

    fun setData(list: List<tbbarang>) {
        benda.clear()
        benda.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(brg: tbbarang)
        fun onUpdate(brg: tbbarang)
        fun onDelete(brg: tbbarang)
    }
}