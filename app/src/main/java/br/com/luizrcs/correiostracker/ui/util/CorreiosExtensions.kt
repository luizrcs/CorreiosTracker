package br.com.luizrcs.correiostracker.ui.util

import android.graphics.*
import android.os.Build.VERSION_CODES.*
import android.view.*
import android.widget.*
import androidx.annotation.*
import androidx.core.content.*
import coil.*
import java.util.*

fun String.capitalizeWord() =
	mapIndexed { index, char -> if (index == 0) char.uppercase() else char.lowercase() }
		.joinToString("")

fun String.formatPostOffice() = split(' ')
	.mapIndexed { index, string ->
		if (index == 0 && string.length <= 3) string.uppercase()
		else string.capitalizeWord()
	}
	.joinToString(" ")

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

fun View.setBackgroundColorFilter(@ColorRes colorId: Int) =
	if (versionIsAtLeast(Q))
		background?.colorFilter = BlendModeColorFilter(ContextCompat.getColor(context, colorId), BlendMode.SRC_ATOP)
	else
		background?.setColorFilter(ContextCompat.getColor(context, colorId), PorterDuff.Mode.SRC_ATOP)