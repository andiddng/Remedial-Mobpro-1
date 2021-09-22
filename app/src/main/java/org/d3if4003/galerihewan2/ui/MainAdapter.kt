package org.d3if4003.galerihewan2.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if4003.galerihewan2.R
import org.d3if4003.galerihewan2.hewan.Hewan
import org.d3if4003.galerihewan2.databinding.ListItemBinding
import org.d3if4003.galerihewan2.network.HewanApiService

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    class ViewHolder(private val binding : ListItemBinding):
        RecyclerView.ViewHolder(binding.root){

        fun bind(hewan: Hewan) {
            with(binding) {
                namaTextView.text = hewan.nama
                latinTextView.text = hewan.namaLatin
                Glide.with(imageView.context)
                    .load(HewanApiService.Companion.HewanApi.getHewanUrl(hewan.imageId))
                    .error(R.drawable.ic_baseline_broken_image_24)
                    .into(imageView)
            }
        }

    }

    private val data = mutableListOf<Hewan>()
    fun updateData(newData: List<Hewan>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])    }

    override fun getItemCount(): Int {
        return data.size
    }
}

