package br.com.luizrcs.correiostracker.ui.util

import androidx.annotation.*
import androidx.compose.ui.graphics.*
import br.com.luizrcs.correiostracker.R
import br.com.luizrcs.correiostracker.repository.*
import br.com.luizrcs.correiostracker.ui.theme.CustomColors.Amber
import br.com.luizrcs.correiostracker.ui.theme.CustomColors.Green
import br.com.luizrcs.correiostracker.ui.theme.CustomColors.Indigo
import br.com.luizrcs.correiostracker.ui.theme.CustomColors.Lime
import br.com.luizrcs.correiostracker.ui.theme.CustomColors.Orange
import br.com.luizrcs.correiostracker.ui.theme.CustomColors.Purple
import br.com.luizrcs.correiostracker.ui.theme.CustomColors.Red

data class StatusStyle(val color: Color, @DrawableRes val icon: Int)

private val nullStyle = StatusStyle(Red, R.drawable.close_outlined_24)

private val styleByStatus = mapOf(
	"PO" to mapOf(null to StatusStyle(Purple, R.drawable.mail_outlined_24)),
	"PAR" to mapOf(
		null to StatusStyle(Indigo, R.drawable.approval_outlined_24),
		11 to StatusStyle(Orange, R.drawable.attach_money_outlined_24),
		14 to StatusStyle(Orange, R.drawable.attach_money_outlined_24),
		17 to StatusStyle(Orange, R.drawable.attach_money_outlined_24),
		30 to StatusStyle(Orange, R.drawable.attach_money_outlined_24),
		31 to StatusStyle(Orange, R.drawable.attach_money_outlined_24),
		32 to StatusStyle(Orange, R.drawable.attach_money_outlined_24),
		33 to StatusStyle(Orange, R.drawable.attach_money_outlined_24),
		32 to StatusStyle(Orange, R.drawable.money_off_outlined_24),
		41 to StatusStyle(Orange, R.drawable.money_off_outlined_24),
	),
	"RO" to mapOf(null to StatusStyle(Amber, R.drawable.local_shipping_outlined_24)),
	"DO" to mapOf(null to StatusStyle(Amber, R.drawable.local_shipping_outlined_24)),
	"LDI" to mapOf(null to StatusStyle(Orange, R.drawable.markunread_mailbox_outlined_24)),
	"OEC" to mapOf(null to StatusStyle(Lime, R.drawable.local_shipping_outlined_24)),
	"BDE" to mapOf(null to StatusStyle(Green, R.drawable.home_outlined_24)),
	"BDI" to mapOf(null to StatusStyle(Green, R.drawable.home_outlined_24)),
	"BDR" to mapOf(null to StatusStyle(Green, R.drawable.home_outlined_24)),
	"EST" to mapOf(null to StatusStyle(Red, R.drawable.reply_outlined_24)),
	"BLQ" to mapOf(
		null to StatusStyle(Red, R.drawable.lock_outlined_24),
		24 to StatusStyle(Red, R.drawable.lock_open_outlined_24),
		44 to StatusStyle(Red, R.drawable.lock_open_outlined_24),
		54 to StatusStyle(Red, R.drawable.lock_open_outlined_24),
		61 to StatusStyle(Red, R.drawable.lock_open_outlined_24),
	)
)

fun ParcelEvent?.statusStyle() = if (this == null) nullStyle else styleByStatus[type]?.let { it[status] ?: it[null] } ?: nullStyle