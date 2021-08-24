package br.com.luizrcs.correiostracker.ui.recyclerview

import androidx.recyclerview.widget.*
import br.com.luizrcs.correiostracker.ui.viewmodel.*

abstract class ParcelsAdapter<T: ParcelsViewModel>(
	protected val viewModel: T
): RecyclerView.Adapter<ParcelItemViewHolder<T>>() {
	
	override fun onBindViewHolder(holder: ParcelItemViewHolder<T>, position: Int) {
		holder.bind(position, viewModel)
	}
	
	override fun getItemCount() = viewModel.parcels.value?.size ?: 0
}