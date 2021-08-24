package br.com.luizrcs.correiostracker.ui.recyclerview

import android.view.*
import br.com.luizrcs.correiostracker.databinding.*
import br.com.luizrcs.correiostracker.ui.viewmodel.*

class FinishedAdapter(viewModel: FinishedViewModel): ParcelsAdapter<FinishedViewModel>(viewModel) {
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParcelItemFinishedViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val parcelItemBinding = ParcelItemBinding.inflate(inflater, parent, false)
		
		return ParcelItemFinishedViewHolder(parcelItemBinding)
	}
}