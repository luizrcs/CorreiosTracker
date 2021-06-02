package br.com.luizrcs.correiostracker.ui.util

import androidx.annotation.*
import br.com.luizrcs.correiostracker.*

data class StatusStyle(@ColorRes val colorId: Int, @DrawableRes val iconId: Int)

private val styleByStatus = mapOf(
	null to mapOf(null to StatusStyle(R.color.red, R.drawable.outline_close_24)),
	"PO" to mapOf(null to StatusStyle(R.color.purple, R.drawable.outline_local_post_office_24)),
	"PAR" to mapOf(
		null to StatusStyle(R.color.indigo, R.drawable.outline_approval_24),
		11 to StatusStyle(R.color.orange, R.drawable.outline_attach_money_24),
		14 to StatusStyle(R.color.orange, R.drawable.outline_attach_money_24),
		17 to StatusStyle(R.color.orange, R.drawable.outline_attach_money_24),
		30 to StatusStyle(R.color.orange, R.drawable.outline_attach_money_24),
		31 to StatusStyle(R.color.orange, R.drawable.outline_attach_money_24),
		32 to StatusStyle(R.color.orange, R.drawable.outline_attach_money_24),
		33 to StatusStyle(R.color.orange, R.drawable.outline_attach_money_24),
		32 to StatusStyle(R.color.orange, R.drawable.outline_money_off_24),
		41 to StatusStyle(R.color.orange, R.drawable.outline_money_off_24)
	),
	"RO" to mapOf(null to StatusStyle(R.color.amber, R.drawable.outline_local_shipping_24)),
	"DO" to mapOf(null to StatusStyle(R.color.amber, R.drawable.outline_local_shipping_24)),
	"LDI" to mapOf(null to StatusStyle(R.color.orange, R.drawable.outline_markunread_mailbox_24)),
	"OEC" to mapOf(null to StatusStyle(R.color.lime, R.drawable.outline_local_post_office_24)),
	"BDE" to mapOf(null to StatusStyle(R.color.green, R.drawable.outline_home_24)),
	"BDI" to mapOf(null to StatusStyle(R.color.green, R.drawable.outline_home_24)),
	"BDR" to mapOf(null to StatusStyle(R.color.green, R.drawable.outline_home_24)),
	"EST" to mapOf(null to StatusStyle(R.color.red, R.drawable.outline_reply_24)),
	"BLQ" to mapOf(
		null to StatusStyle(R.color.red, R.drawable.outline_lock_24),
		24 to StatusStyle(R.color.red, R.drawable.outline_lock_open_24),
		44 to StatusStyle(R.color.red, R.drawable.outline_lock_open_24),
		54 to StatusStyle(R.color.red, R.drawable.outline_lock_open_24),
		61 to StatusStyle(R.color.red, R.drawable.outline_lock_open_24)
	)
)

fun statusStyle(type: String?, status: Int?) =
	styleByStatus[type]?.let { it[status] ?: it[null] } ?: styleByStatus[null]!![null]!!