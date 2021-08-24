package br.com.luizrcs.correiostracker.ui.recyclerview

import androidx.recyclerview.widget.*
import br.com.luizrcs.correiostracker.databinding.*
import br.com.luizrcs.correiostracker.ui.viewmodel.*

abstract class ParcelItemViewHolder<T: ParcelsViewModel>(protected val itemBinding: ParcelItemBinding):
	RecyclerView.ViewHolder(itemBinding.root) {
	
	abstract fun bind(index: Int, viewModel: T)
}