package br.com.luizrcs.correiostracker.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*

val Roboto = FontFamily.Default

val CorreiosTrackerTypography = Typography(
	displayLarge = TextStyle(
		fontFamily = Roboto,
		fontWeight = W400,
		fontSize = 57.sp,
		lineHeight = 64.sp,
		letterSpacing = (-0.25).sp,
	),
	displayMedium = TextStyle(
		fontFamily = Roboto,
		fontWeight = W400,
		fontSize = 45.sp,
		lineHeight = 52.sp,
		letterSpacing = 0.sp,
	),
	displaySmall = TextStyle(
		fontFamily = Roboto,
		fontWeight = W400,
		fontSize = 36.sp,
		lineHeight = 44.sp,
		letterSpacing = 0.sp,
	),
	headlineLarge = TextStyle(
		fontFamily = Roboto,
		fontWeight = W400,
		fontSize = 32.sp,
		lineHeight = 40.sp,
		letterSpacing = 0.sp,
	),
	headlineMedium = TextStyle(
		fontFamily = Roboto,
		fontWeight = W400,
		fontSize = 28.sp,
		lineHeight = 36.sp,
		letterSpacing = 0.sp,
	),
	headlineSmall = TextStyle(
		fontFamily = Roboto,
		fontWeight = W400,
		fontSize = 24.sp,
		lineHeight = 32.sp,
		letterSpacing = 0.sp,
	),
	titleLarge = TextStyle(
		fontFamily = Roboto,
		fontWeight = W400,
		fontSize = 22.sp,
		lineHeight = 28.sp,
		letterSpacing = 0.sp,
	),
	titleMedium = TextStyle(
		fontFamily = Roboto,
		fontWeight = Medium,
		fontSize = 16.sp,
		lineHeight = 24.sp,
		letterSpacing = 0.1.sp,
	),
	titleSmall = TextStyle(
		fontFamily = Roboto,
		fontWeight = Medium,
		fontSize = 14.sp,
		lineHeight = 20.sp,
		letterSpacing = 0.1.sp,
	),
	bodyLarge = TextStyle(
		fontFamily = Roboto,
		fontWeight = W400,
		fontSize = 16.sp,
		lineHeight = 24.sp,
		letterSpacing = 0.5.sp,
	),
	bodyMedium = TextStyle(
		fontFamily = Roboto,
		fontWeight = W400,
		fontSize = 14.sp,
		lineHeight = 20.sp,
		letterSpacing = 0.25.sp,
	),
	bodySmall = TextStyle(
		fontFamily = Roboto,
		fontWeight = W400,
		fontSize = 12.sp,
		lineHeight = 16.sp,
		letterSpacing = 0.4.sp,
	),
	labelLarge = TextStyle(
		fontFamily = Roboto,
		fontWeight = Medium,
		fontSize = 14.sp,
		lineHeight = 20.sp,
		letterSpacing = 0.1.sp,
	),
	labelMedium = TextStyle(
		fontFamily = Roboto,
		fontWeight = Medium,
		fontSize = 12.sp,
		lineHeight = 16.sp,
		letterSpacing = 0.5.sp,
	),
	labelSmall = TextStyle(
		fontFamily = Roboto,
		fontWeight = Medium,
		fontSize = 11.sp,
		lineHeight = 16.sp,
		letterSpacing = 0.5.sp,
	),
)

@Preview
@Composable
fun PreviewCorreiosTrackerTypography() {
	with(CorreiosTrackerTypography) {
		Surface {
			Column {
				Text("Display Large", style = displayLarge)
				Text("Display Medium", style = displayMedium)
				Text("Display Small", style = displaySmall)
				Text("Headline Large", style = headlineLarge)
				Text("Headline Medium", style = headlineMedium)
				Text("Headline Small", style = headlineSmall)
				Text("Title Large", style = titleLarge)
				Text("Title Medium", style = titleMedium)
				Text("Title Small", style = titleSmall)
				Text("Body Large", style = bodyLarge)
				Text("Body Medium", style = bodyMedium)
				Text("Body Small", style = bodySmall)
				Text("Label Large", style = labelLarge)
				Text("Label Medium", style = labelMedium)
				Text("Label Small", style = labelSmall)
			}
		}
	}
}