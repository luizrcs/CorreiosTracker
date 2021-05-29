package br.com.luizrcs.correiostracker.ui

import android.view.View.*
import android.widget.*
import androidx.recyclerview.widget.*
import br.com.luizrcs.correiostracker.databinding.*
import br.com.luizrcs.correiostracker.repository.*

class ParcelItemViewHolder(private val itemBinding: ParcelItemBinding): RecyclerView.ViewHolder(itemBinding.root) {
	
	fun bind(parcel: Object) {
		val event = parcel.events.firstOrNull()
		
		itemBinding.name.text = parcel.trackingCode.formatTrackingCode()
		itemBinding.status.text = event?.description ?: ""
		itemBinding.postOffice.text = event?.postOffice?.name?.formatPostOffice() ?: ""
		
		if (parcel.countryCode == "BR") {
			itemBinding.category.apply {
				visibility = VISIBLE
				text = parcel.serviceType.uppercase()
			}
			itemBinding.flagIcon.visibility = GONE
		} else {
			itemBinding.category.visibility = GONE
			itemBinding.flagIcon.apply {
				visibility = VISIBLE
				setFlag(parcel.countryCode)
			}
		}
		
		itemBinding.date.text = event?.date?.substringBeforeLast('/') ?: ""
		itemBinding.time.text = event?.time ?: ""
	}
}

private fun String.capitalizeWord() =
	mapIndexed { index, char -> if (index == 0) char.uppercase() else char.lowercase() }
		.joinToString("")

private fun String.formatPostOffice() = split(' ')
	.mapIndexed { index, string ->
		if (index == 0 && string.length <= 3) string.uppercase()
		else string.capitalizeWord()
	}
	.joinToString(" ")

private fun String.formatTrackingCode(): String {
	val serviceCode = substring(0 .. 1)
	val part1 = substring(2 .. 4)
	val part2 = substring(5 .. 7)
	val part3 = substring(8 .. 10)
	val countryCode = substring(11)
	
	return "$serviceCode $part1 $part2 $part3 $countryCode"
}

private fun ImageView.setFlag(countryCode: String) = apply {
	val flagResource = context.resources.getIdentifier(countryCode.lowercase(), "drawable", context.packageName)
	setImageResource(flagResource)
}