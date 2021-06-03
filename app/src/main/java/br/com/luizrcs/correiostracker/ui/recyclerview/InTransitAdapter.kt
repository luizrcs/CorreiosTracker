package br.com.luizrcs.correiostracker.ui.recyclerview

import android.view.*
import androidx.recyclerview.widget.*
import br.com.luizrcs.correiostracker.databinding.*
import br.com.luizrcs.correiostracker.ui.viewmodel.*

class InTransitAdapter(
	private val viewModel: InTransitViewModel
): RecyclerView.Adapter<ParcelItemViewHolder>() {
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParcelItemViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val parcelItemBinding = ParcelItemBinding.inflate(inflater, parent, false)
		
		return ParcelItemViewHolder(parcelItemBinding)
	}
	
	override fun onBindViewHolder(holder: ParcelItemViewHolder, position: Int) {
		holder.bind(position, viewModel)
	}
	
	override fun getItemCount() = viewModel.parcels.value?.size ?: 0
}