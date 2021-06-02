package br.com.luizrcs.correiostracker.ui.recyclerview

import android.view.*
import android.view.View.*
import androidx.core.content.*
import androidx.core.content.res.*
import androidx.recyclerview.widget.*
import br.com.luizrcs.correiostracker.*
import br.com.luizrcs.correiostracker.databinding.*
import br.com.luizrcs.correiostracker.repository.*
import br.com.luizrcs.correiostracker.ui.util.*
import br.com.luizrcs.correiostracker.ui.viewmodel.*
import com.google.android.material.dialog.*

class ParcelItemViewHolder(private val itemBinding: ParcelItemBinding): RecyclerView.ViewHolder(itemBinding.root) {
	
	fun bind(parcel: Parcel, viewModel: InTransitViewModel) {
		val event = parcel.parcelEvents?.firstOrNull()
		
		val statusStyle = statusStyle(event?.type, event?.status)
		
		itemBinding.name.text = parcel.name
		
		itemBinding.statusIcon.setImageResource(statusStyle.iconId)
		itemBinding.statusIconContainer.setBackgroundColorFilter(statusStyle.colorId)
		
		itemBinding.status.apply {
			text = event?.description ?: context.getString(R.string.parcel_unknown)
			setTextColor(ContextCompat.getColor(context, statusStyle.colorId))
		}
		
		itemBinding.postOffice.apply {
			text = event?.fromOffice?.name?.formatPostOffice()
			visibility = if (text.isNullOrEmpty()) GONE else VISIBLE
		}
		
		if (parcel.countryCode == "BR") {
			itemBinding.category.apply {
				if (parcel.serviceType.isNotEmpty()) text = parcel.serviceType.uppercase()
				else visibility = GONE
			}
			itemBinding.flagIcon.visibility = GONE
		} else {
			itemBinding.category.visibility = GONE
			itemBinding.flagIcon.setFlag(parcel.countryCode)
		}
		
		itemBinding.date.text = event?.date?.substringBeforeLast('/')
		itemBinding.time.text = event?.time
		
		itemBinding.root.apply {
			setOnLongClickListener {
				val dialogBinding = DialogEditParcelBinding.inflate(LayoutInflater.from(context))
				
				val dialog = MaterialAlertDialogBuilder(context)
					.setView(dialogBinding.root)
					.setBackground(ResourcesCompat.getDrawable(resources, R.drawable.dialog_shape, null))
					.show()
				
				dialogBinding.name.setText(parcel.name)
				dialogBinding.code.setText(parcel.trackingCode)
				
				dialogBinding.cancel.setOnClickListener { dialog.dismiss() }
				dialogBinding.confirm.setOnClickListener {
					val code = parcel.trackingCode
					val name = dialogBinding.name.text
						?.let { if (it.isNotBlank()) it.toString().trim() else code.formatTrackingCode() }
						?: code.formatTrackingCode()
					viewModel.editParcel(name, code)
					dialog.dismiss()
				}
				
				true
			}
			
			setOnClickListener {
				// TODO Another screen
			}
		}
	}
}