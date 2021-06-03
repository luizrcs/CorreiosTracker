package br.com.luizrcs.correiostracker.ui.recyclerview

import android.view.*
import androidx.recyclerview.widget.*
import br.com.luizrcs.correiostracker.databinding.*
import br.com.luizrcs.correiostracker.repository.*

class ParcelDetailsAdapter(private val parcel: Parcel): RecyclerView.Adapter<ParcelDetailsItemViewHolder>() {
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParcelDetailsItemViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val parcelDetailsItemBinding = ParcelDetailsItemBinding.inflate(inflater, parent, false)
		
		return ParcelDetailsItemViewHolder(parcelDetailsItemBinding)
	}
	
	override fun onBindViewHolder(holder: ParcelDetailsItemViewHolder, position: Int) {
		val parcelEvents = parcel.parcelEvents!!
		holder.bind(position, parcelEvents.lastIndex, parcelEvents[position])
	}
	
	override fun getItemCount() = parcel.parcelEvents?.size ?: 0
}