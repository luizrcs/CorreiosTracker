package br.com.luizrcs.correiostracker.ui.util

import androidx.annotation.*
import br.com.luizrcs.correiostracker.R.color.*
import br.com.luizrcs.correiostracker.R.drawable.*
import br.com.luizrcs.correiostracker.repository.*

data class StatusStyle(@ColorRes val colorId: Int, @DrawableRes val iconId: Int)

private val styleByStatus = mapOf(
	null to mapOf(null to StatusStyle(red, outline_close_24)),
	"PO" to mapOf(null to StatusStyle(purple, outline_local_post_office_24)),
	"PAR" to mapOf(
		null to StatusStyle(indigo, outline_approval_24),
		11 to StatusStyle(orange, outline_attach_money_24),
		14 to StatusStyle(orange, outline_attach_money_24),
		17 to StatusStyle(orange, outline_attach_money_24),
		30 to StatusStyle(orange, outline_attach_money_24),
		31 to StatusStyle(orange, outline_attach_money_24),
		32 to StatusStyle(orange, outline_attach_money_24),
		33 to StatusStyle(orange, outline_attach_money_24),
		32 to StatusStyle(orange, outline_money_off_24),
		41 to StatusStyle(orange, outline_money_off_24)
	),
	"RO" to mapOf(null to StatusStyle(amber, outline_local_shipping_24)),
	"DO" to mapOf(null to StatusStyle(amber, outline_local_shipping_24)),
	"LDI" to mapOf(null to StatusStyle(orange, outline_markunread_mailbox_24)),
	"OEC" to mapOf(null to StatusStyle(lime, outline_local_post_office_24)),
	"BDE" to mapOf(null to StatusStyle(green, outline_home_24)),
	"BDI" to mapOf(null to StatusStyle(green, outline_home_24)),
	"BDR" to mapOf(null to StatusStyle(green, outline_home_24)),
	"EST" to mapOf(null to StatusStyle(red, outline_reply_24)),
	"BLQ" to mapOf(
		null to StatusStyle(red, outline_lock_24),
		24 to StatusStyle(red, outline_lock_open_24),
		44 to StatusStyle(red, outline_lock_open_24),
		54 to StatusStyle(red, outline_lock_open_24),
		61 to StatusStyle(red, outline_lock_open_24)
	)
)

fun ParcelEvent?.statusStyle() =
	if (this == null) styleByStatus[null]!![null]!!
	else styleByStatus[type]?.let { it[status] ?: it[null] } ?: styleByStatus[null]!![null]!!