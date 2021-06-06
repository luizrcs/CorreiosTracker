package br.com.luizrcs.correiostracker.ui.recyclerview

import android.view.View.*
import androidx.core.content.*
import androidx.recyclerview.widget.*
import br.com.luizrcs.correiostracker.databinding.*
import br.com.luizrcs.correiostracker.repository.*
import br.com.luizrcs.correiostracker.ui.util.*

class ParcelDetailsItemViewHolder(private val itemBinding: ParcelDetailsItemBinding):
	RecyclerView.ViewHolder(itemBinding.root) {
	
	fun bind(index: Int, lastIndex: Int, event: ParcelEvent) {
		val statusStyle = statusStyle(event.type, event.status)
		val color = ContextCompat.getColor(itemBinding.root.context, statusStyle.colorId)
		
		itemBinding.timelineTo.apply {
			setBackgroundColor(color)
			visibility = if (index == 0) GONE else VISIBLE
		}
		
		itemBinding.timelineFrom.apply {
			setBackgroundColor(color)
			visibility = if (index == lastIndex) GONE else VISIBLE
		}
		
		itemBinding.statusIcon.setImageResource(statusStyle.iconId)
		itemBinding.statusIconContainer.setBackgroundColorFilter(color)
		
		itemBinding.status.apply {
			text = event.description.formatEventDescription()
			setTextColor(color)
		}
		
		itemBinding.details.apply {
			text = event.details
			visibility = if (event.details != null) VISIBLE else GONE
		}
		
		itemBinding.postOffice.apply {
			text = event.toOffice?.firstOrNull()?.formatPostOffice()
				?: event.fromOffice.formatPostOffice()
			visibility = VISIBLE
		}
		
		itemBinding.date.text = event.date.substringBeforeLast('/')
		itemBinding.time.text = event.time
	}
}
