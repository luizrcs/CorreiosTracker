package br.com.luizrcs.correiostracker.ui.screen

import androidx.compose.runtime.*
import androidx.navigation.*

open class Screen(
	val route: String,
	// val content: @Composable (NavController) -> Unit,
) {
	operator fun component1() = route
	// operator fun component2() = content
}