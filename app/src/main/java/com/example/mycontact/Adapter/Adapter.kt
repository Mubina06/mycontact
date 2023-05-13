package com.example.mycontact.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontact.Data.for_item
import com.example.mycontact.R

class Adapter(var list:MutableList<for_item>, var contInterface: ContactInterface) : RecyclerView.Adapter<Adapter.ContactHolder>() {

    var onItemClick : ((for_item) -> Unit)? = null
    fun FilteredList(list: MutableList<for_item>){
        this.list = list
        notifyDataSetChanged()
    }
    class ContactHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var name: TextView = itemView.findViewById(R.id.name)
        var phone_number: TextView = itemView.findViewById(R.id.phone)
        var contactlayout: ConstraintLayout = itemView.findViewById(R.id.c_lay)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        var holder = ContactHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false))
        return holder
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        var item = list[position]
        holder.name.text = item.name
        holder.phone_number.text = item.phone_number
        holder.contactlayout.setOnClickListener {
            contInterface.onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface ContactInterface{
        fun onClick(adapter: for_item){

        }
    }
}