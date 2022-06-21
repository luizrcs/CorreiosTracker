package br.com.luizrcs.correiostracker.ui.util

import androidx.compose.material.icons.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.*
import br.com.luizrcs.correiostracker.repository.*
import br.com.luizrcs.correiostracker.ui.theme.CustomColors.Amber
import br.com.luizrcs.correiostracker.ui.theme.CustomColors.Green
import br.com.luizrcs.correiostracker.ui.theme.CustomColors.Indigo
import br.com.luizrcs.correiostracker.ui.theme.CustomColors.Lime
import br.com.luizrcs.correiostracker.ui.theme.CustomColors.Orange
import br.com.luizrcs.correiostracker.ui.theme.CustomColors.Purple
import br.com.luizrcs.correiostracker.ui.theme.CustomColors.Red

data class StatusStyle(val color: Color, val icon: ImageVector)

private val nullStyle = StatusStyle(Red, Icons.Outlined.Close)

private val styleByStatus = mapOf(
	"PO" to mapOf(null to StatusStyle(Purple, Icons.Outlined.LocalPostOffice)),
	"PAR" to mapOf(
		null to StatusStyle(Indigo, Icons.Outlined.Approval),
		11 to StatusStyle(Orange, Icons.Outlined.AttachMoney),
		14 to StatusStyle(Orange, Icons.Outlined.AttachMoney),
		17 to StatusStyle(Orange, Icons.Outlined.AttachMoney),
		30 to StatusStyle(Orange, Icons.Outlined.AttachMoney),
		31 to StatusStyle(Orange, Icons.Outlined.AttachMoney),
		32 to StatusStyle(Orange, Icons.Outlined.AttachMoney),
		33 to StatusStyle(Orange, Icons.Outlined.AttachMoney),
		32 to StatusStyle(Orange, Icons.Outlined.MoneyOff),
		41 to StatusStyle(Orange, Icons.Outlined.MoneyOff)
	),
	"RO" to mapOf(null to StatusStyle(Amber, Icons.Outlined.LocalShipping)),
	"DO" to mapOf(null to StatusStyle(Amber, Icons.Outlined.LocalShipping)),
	"LDI" to mapOf(null to StatusStyle(Orange, Icons.Outlined.MarkunreadMailbox)),
	"OEC" to mapOf(null to StatusStyle(Lime, Icons.Outlined.LocalPostOffice)),
	"BDE" to mapOf(null to StatusStyle(Green, Icons.Outlined.Home)),
	"BDI" to mapOf(null to StatusStyle(Green, Icons.Outlined.Home)),
	"BDR" to mapOf(null to StatusStyle(Green, Icons.Outlined.Home)),
	"EST" to mapOf(null to StatusStyle(Red, Icons.Outlined.Reply)),
	"BLQ" to mapOf(
		null to StatusStyle(Red, Icons.Outlined.Lock),
		24 to StatusStyle(Red, Icons.Outlined.LockOpen),
		44 to StatusStyle(Red, Icons.Outlined.LockOpen),
		54 to StatusStyle(Red, Icons.Outlined.LockOpen),
		61 to StatusStyle(Red, Icons.Outlined.LockOpen)
	)
)

fun ParcelEvent?.statusStyle() = if (this == null) nullStyle else styleByStatus[type]?.let { it[status] ?: it[null] } ?: nullStyle