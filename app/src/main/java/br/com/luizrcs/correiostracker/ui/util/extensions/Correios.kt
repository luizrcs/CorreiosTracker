@file:OptIn(
	ExperimentalTime::class
)

package br.com.luizrcs.correiostracker.ui.util

import android.graphics.*
import android.os.Build.VERSION_CODES.*
import android.view.*
import android.widget.*
import androidx.annotation.*
import br.com.luizrcs.correiostracker.repository.*
import br.com.luizrcs.correiostracker.ui.util.extensions.*
import coil.*
import org.joda.time.*
import java.text.*
import java.util.*
import java.util.Calendar.*
import kotlin.OptIn
import kotlin.time.*

private val dateFormat = SimpleDateFormat("dd/MM/yyyy")

fun PostOffice.formatPostOffice() = name.formatPostOffice() +
		when {
			address.city != null  ->
				if (address.city.lowercase() in name.lowercase()) ", ${address.state!!.uppercase()}"
				else ", ${address.city.capitalizeWords()} - ${address.state!!.uppercase()}"
			address.state != null -> ", ${address.state.uppercase()}"
			else                  -> ""
		}

fun Parcel.daysElapsed() = parcelEvents?.let {
	val start = DateTime(dateFormat.parse(it.last().date))
	val end = DateTime()
	Days.daysBetween(start, end).days
}

fun String.formatDate() =
	if (getInstance().get(YEAR).toString() != substringAfterLast('/')) this
	else substringBeforeLast('/')

fun String.capitalizeWord() =
	mapIndexed { index, char -> if (index == 0) char.uppercase() else char.lowercase() }
		.joinToString("")

fun String.capitalizeWords() = split(" ").joinToString(" ") { it.capitalizeWord() }

fun String.formatEventDescription() = substringAfter("Objeto ")
	.replaceFirstChar { if (it.isLowerCase()) it.uppercase() else it.toString() }

fun String.formatPostOffice() = split(' ')
	.run {
		mapIndexed { index, string ->
			if (string.length <= 3) {
				if (index == 0 || index == lastIndex) string.uppercase()
				else string.lowercase()
			} else string.capitalizeWord()
		}.joinToString(" ")
	}

fun String.formatTrackingCode(): String {
	val serviceCode = substring(0 .. 1)
	val part1 = substring(2 .. 4)
	val part2 = substring(5 .. 7)
	val part3 = substring(8 .. 10)
	val countryCode = substring(11)
	
	return "$serviceCode $part1 $part2 $part3 $countryCode"
}

fun String.toDate(): Date {
	val day = substring(0 .. 1).toInt()
	val month = substring(2 .. 3).toInt() - 1
	val year = substring(4 .. 7).toInt()
	val hour = substring(8 .. 9).toInt()
	val minute = substring(10 .. 11).toInt()
	val second = substring(12 .. 13).toInt()
	
	val calendar = GregorianCalendar()
	calendar.set(year, month, day, hour, minute, second)
	
	return calendar.time
}

fun ImageView.setFlag(countryCode: String) = apply {
	val flagResource = context.resources.getIdentifier(countryCode.lowercase(), "drawable", context.packageName)
	load(flagResource) { crossfade(true) }
}

fun View.setBackgroundColorFilter(@ColorInt color: Int) =
	if (versionIsAtLeast(Q))
		background?.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
	else
		background?.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)