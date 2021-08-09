package com.example.ompaaloompa.utils

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.ompaaloompa.R
import com.example.ompaaloompa.data.models.Oompa

class OompaAdapter :
    ListAdapter<Oompa, OompaAdapter.ViewHolder>( OompaDiffCallback() ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_oompa, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val oompa = getItem(position)
        holder.bind(oompa)

        try {
            Glide
                .with(holder.itemView)
                .load(oompa.image)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(holder.ivItem)
        } catch (ex: Exception) {
            Log.e("ImageGlide Error", ex.message.toString())
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        /*
        Tuve un problema con el bindeo de datos en Adapter. No se me refrescaba la view, así que
        volví a la forma clásica. No tuve tiempo de investigarlo a fondo...
         */
        val tvName: TextView = view.findViewById(R.id.tv_item_name)
        val tvLastName: TextView = view.findViewById(R.id.tv_item_lastname)
        val tvProfession: TextView = view.findViewById(R.id.tv_item_profession)
        val ivItem: ImageView = view.findViewById(R.id.iv_item_oompa)
        val view = view;

        fun bind (oompa: Oompa) {
            tvName.text = oompa.first_name
            tvLastName.text = oompa.last_name
            tvProfession.text = oompa.profession

            view.setOnClickListener { view ->
                val bundle = Bundle()
                bundle.putInt("id", oompa.id)
                Navigation.findNavController(view).navigate(R.id.action_oompasFragment_to_oompaInfoFragment, bundle)
            }
        }
    }

}

private class OompaDiffCallback : DiffUtil.ItemCallback<Oompa>() {

    override fun areItemsTheSame(oldItem: Oompa, newItem: Oompa): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Oompa, newItem: Oompa): Boolean {
        return oldItem == newItem
    }

}