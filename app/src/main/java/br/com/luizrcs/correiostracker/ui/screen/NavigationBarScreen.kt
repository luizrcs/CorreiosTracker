package br.com.luizrcs.correiostracker.ui.screen

import androidx.annotation.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.*
import androidx.navigation.*

open class NavigationBarScreen(
	route: String,
	// content: @Composable (NavController) -> Unit,
	val navigationBarItemDescriptor: NavigationBarItemDescriptor,
): Screen(
	route,
	// content,
) {
	// operator fun component3() = navigationBarItemDescriptor
	operator fun component2() = navigationBarItemDescriptor
}

data class NavigationBarItemDescriptor(
	@DrawableRes val iconActive: Int,
	@DrawableRes val iconInactive: Int,
	@StringRes val label: Int,
)