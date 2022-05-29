package br.com.luizrcs.correiostracker.ui.util.extensions

import androidx.navigation.*
import androidx.navigation.NavDestination.Companion.hierarchy

val NavBackStackEntry.hierarchy get() = this.destination.hierarchy
fun NavBackStackEntry.isInHierarchy(route: String) = hierarchy.any { it.route == route }