package com.example.ompaaloompa.utils

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ompaaloompa.R
import com.example.ompaaloompa.data.models.Oompa
import java.lang.Exception

class OompaAdapter(var oompas: MutableList<Oompa>): RecyclerView.Adapter<OompaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_oompa, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return oompas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val oompa = oompas.get(position)
        holder.tvName.text = oompa.first_name
        holder.tvLastName.text = oompa.last_name
        holder.tvProfession.text = oompa.profession

        try {
            Glide
                .with(holder.itemView)
                .load(oompa.image)
                .fitCenter()
                .into(holder.ivItem)
        } catch (ex: Exception) {
            Log.e("ImageGlide Error", ex.message.toString())
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvName: TextView
        val tvLastName: TextView
        val tvProfession: TextView
        val ivItem: ImageView

        init {
            tvName = view.findViewById(R.id.tv_item_name)
            tvLastName = view.findViewById(R.id.tv_item_lastname)
            tvProfession = view.findViewById(R.id.tv_item_profession)
            ivItem = view.findViewById(R.id.iv_item_oompa)
        }
    }

}