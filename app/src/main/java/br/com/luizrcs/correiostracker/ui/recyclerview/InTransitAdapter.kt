package br.com.luizrcs.correiostracker.ui.recyclerview

import android.view.*
import br.com.luizrcs.correiostracker.databinding.*
import br.com.luizrcs.correiostracker.ui.viewmodel.*

class InTransitAdapter(viewModel: InTransitViewModel): ParcelsAdapter<InTransitViewModel>(viewModel) {
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParcelItemInTransitViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val parcelItemBinding = ParcelItemBinding.inflate(inflater, parent, false)
		
		return ParcelItemInTransitViewHolder(parcelItemBinding)
	}
}