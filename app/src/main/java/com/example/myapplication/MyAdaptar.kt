package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdaptar( var con : Context , var list: List<Reclamation>) : RecyclerView.Adapter<MyAdaptar.ViewHolder>()
{
    inner class ViewHolder(v : View) : RecyclerView.ViewHolder(v)
    {
     var nom = v.findViewById<TextView>(R.id.nom)
        var prenom = v.findViewById<TextView>(R.id.prenom)
        var email = v.findViewById<TextView>(R.id.email1)
        var description = v.findViewById<TextView>(R.id.description)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      var view = LayoutInflater.from(con).inflate(R.layout.list_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
return list.count()   }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
}