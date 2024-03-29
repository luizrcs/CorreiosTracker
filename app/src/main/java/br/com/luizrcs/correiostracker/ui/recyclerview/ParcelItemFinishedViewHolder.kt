package br.com.luizrcs.correiostracker.ui.recyclerview

import android.content.*
import android.view.*
import android.view.View.*
import android.widget.*
import androidx.core.content.*
import androidx.core.content.res.*
import androidx.navigation.*
import br.com.luizrcs.correiostracker.R
import br.com.luizrcs.correiostracker.databinding.*
import br.com.luizrcs.correiostracker.ui.fragment.*
import br.com.luizrcs.correiostracker.ui.util.*
import br.com.luizrcs.correiostracker.ui.util.extensions.*
import br.com.luizrcs.correiostracker.ui.viewmodel.*
import com.google.android.material.dialog.*

class ParcelItemFinishedViewHolder(itemBinding: ParcelItemBinding):
	ParcelItemViewHolder<FinishedViewModel>(itemBinding) {
	
	override fun bind(filteredIndex: Int, viewModel: FinishedViewModel) {
		val parcel = viewModel.filteredParcels.value?.get(filteredIndex) ?: return
		val event = parcel.parcelEvents?.firstOrNull()
		val valid = event != null
		
		val statusStyle = event.statusStyle()
		// val color = ContextCompat.getColor(itemBinding.root.context, statusStyle.colorId)
		
		itemBinding.name.text = parcel.name
		
		// itemBinding.statusIcon.setImageResource(statusStyle.iconId)
		// itemBinding.statusIconContainer.setBackgroundColorFilter(color)
		
		itemBinding.status.apply {
			text = event?.description?.formatEventDescription() ?: context.getString(R.string.parcelUnknown)
			// setTextColor(color)
		}
		
		itemBinding.postOfficeIcon.visibility = if (valid) VISIBLE else GONE
		
		itemBinding.postOffice.apply {
			text = event?.toOffice?.firstOrNull()?.name?.formatPostOffice()
				?: event?.fromOffice?.name?.formatPostOffice()
			visibility = if (valid) VISIBLE else GONE
		}
		
		if (parcel.countryCode == "BR") {
			itemBinding.category.apply {
				if (valid) {
					text = parcel.serviceType.substringAfterLast(' ').uppercase()
					visibility = VISIBLE
				} else visibility = GONE
			}
			itemBinding.flagIcon.visibility = GONE
		} else {
			itemBinding.category.visibility = GONE
			itemBinding.flagIcon.apply {
				// setFlag(parcel.countryCode)
				visibility = VISIBLE
			}
		}
		
		if (valid) {
			itemBinding.dateTime.visibility = VISIBLE
			itemBinding.date.text = event!!.date.formatDate()
			itemBinding.time.text = event.time
		} else itemBinding.dateTime.visibility = GONE
		
		itemBinding.root.apply {
			setOnLongClickListener {
				val dialogBinding = DialogEditParcelBinding.inflate(LayoutInflater.from(context))
				
				val dialog = MaterialAlertDialogBuilder(context)
					.setView(dialogBinding.root)
					.setBackground(ResourcesCompat.getDrawable(resources, R.drawable.dialog_shape, null))
					.show()
				
				dialogBinding.delete.setOnClickListener {
					val code = parcel.trackingCode
					viewModel.removeParcel(code)
					
					dialog.dismiss()
					
					Toast.makeText(context, R.string.dialog_edit_parcel_deleted, Toast.LENGTH_SHORT).show()
				}
				
				dialogBinding.name.setText(parcel.name)
				dialogBinding.code.setText(parcel.trackingCode)
				
				dialogBinding.copyCode.setOnClickListener {
					val clipboardManager = ContextCompat.getSystemService(context, ClipboardManager::class.java)
					val clipData = ClipData.newPlainText("trackingCode", parcel.trackingCode)
					clipboardManager?.setPrimaryClip(clipData)
					
					Toast.makeText(context, R.string.dialogParcelCodeCopied, Toast.LENGTH_SHORT).show()
				}
				
				dialogBinding.cancel.setOnClickListener { dialog.dismiss() }
				dialogBinding.confirm.setOnClickListener {
					val code = parcel.trackingCode
					val name = dialogBinding.name.text
						?.let { if (it.isNotBlank()) it.toString().trim() else code.formatTrackingCode() }
						?: code.formatTrackingCode()
					viewModel.editParcel(name, code)
					dialog.dismiss()
					
					Toast.makeText(context, R.string.dialog_edit_parcel_saved, Toast.LENGTH_SHORT).show()
				}
				
				true
			}
			
			if (valid) {
				setOnClickListener {
					val index = viewModel.parcels.indexOf(parcel)
					val action = FinishedFragmentDirections.finishedToParcelDetails(index)
					findNavController().navigate(action)
				}
			}
		}
	}
}