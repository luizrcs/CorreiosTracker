package br.com.luizrcs.correiostracker.ui

import android.view.*
import androidx.recyclerview.widget.*
import br.com.luizrcs.correiostracker.databinding.*
import br.com.luizrcs.correiostracker.repository.*

class InTransitAdapter(private val correiosRepository: CorreiosRepository):
	RecyclerView.Adapter<ParcelItemViewHolder>() {
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParcelItemViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val parcelItemBinding = ParcelItemBinding.inflate(inflater, parent, false)
		
		return ParcelItemViewHolder(parcelItemBinding)
	}
	
	override fun onBindViewHolder(holder: ParcelItemViewHolder, position: Int) {
		holder.bind(correiosRepository.parcels[position])
	}
	
	override fun getItemCount() = correiosRepository.parcels.size
}