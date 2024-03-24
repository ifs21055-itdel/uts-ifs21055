package com.ifs21055.dinopedia

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21055.dinopedia.databinding.ItemRowKeluargaBinding

class ListKeluargaAdapter(private val listKeluarga: ArrayList<Keluarga>) :
    RecyclerView.Adapter<ListKeluargaAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback:
                               OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType:
    Int): ListViewHolder {
        val binding =
            ItemRowKeluargaBinding.inflate(LayoutInflater.from(viewGroup.context),
                viewGroup, false)
        return ListViewHolder(binding)
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val keluarga = listKeluarga[position]
        holder.binding.ivItemFamily.setImageResource(keluarga.icon)
        holder.binding.tvItemFamily.text = keluarga.name
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listKeluarga[holder.adapterPosition])
        }
    }


    override fun getItemCount(): Int = listKeluarga.size
    class ListViewHolder(var binding: ItemRowKeluargaBinding) :
        RecyclerView.ViewHolder(binding.root)
    interface OnItemClickCallback {
        fun onItemClicked(data: Keluarga)
    }
}
