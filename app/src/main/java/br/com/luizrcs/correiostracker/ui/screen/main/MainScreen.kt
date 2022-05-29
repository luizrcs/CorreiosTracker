@file:OptIn(
	ExperimentalMaterial3Api::class,
)

package br.com.luizrcs.correiostracker.ui.screen

import android.annotation.*
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import androidx.navigation.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import br.com.luizrcs.correiostracker.R
import br.com.luizrcs.correiostracker.ui.screen.main.*
import br.com.luizrcs.correiostracker.ui.theme.*
import br.com.luizrcs.correiostracker.ui.util.extensions.*

sealed class MainScreen(
	route: String,
	content: @Composable (NavController) -> Unit,
	navigationBarItemDescriptor: NavigationBarItemDescriptor,
): NavigationBarScreen(
	route,
	content,
	navigationBarItemDescriptor
) {
	object InTransit: MainScreen(
		route = "inTransit",
		navigationBarItemDescriptor = NavigationBarItemDescriptor(
			iconActive = Icons.Filled.LocalShipping,
			iconInactive = Icons.Outlined.LocalShipping,
			label = R.string.mainNavigationBarItemInTransit
		),
		content = { navController -> InTransitScreen(navController = navController) }
	)
	
	object Finished: MainScreen(
		route = "finished",
		navigationBarItemDescriptor = NavigationBarItemDescriptor(
			iconActive = Icons.Filled.CheckCircle,
			iconInactive = Icons.Outlined.CheckCircle,
			label = R.string.mainNavigationBarItemFinished
		),
		content = {
			Text(text = stringResource(id = R.string.mainNavigationBarItemFinished))
		}
	)
	
	object Tools: MainScreen(
		route = "tools",
		navigationBarItemDescriptor = NavigationBarItemDescriptor(
			iconActive = Icons.Filled.Handyman,
			iconInactive = Icons.Outlined.Handyman,
			label = R.string.mainNavigationBarItemTools
		),
		content = {
			Text(text = stringResource(id = R.string.mainNavigationBarItemTools))
		}
	)
}

val mainScreens = listOf(
	MainScreen.InTransit,
	MainScreen.Finished,
	MainScreen.Tools,
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
	val navController = rememberNavController()
	
	Scaffold(
		topBar = { MainTopAppBar() },
		floatingActionButton = {
			MainExtendedFloatingActionButton(
				navController = navController,
			)
		},
		bottomBar = {
			MainNavigationBar(
				navController = navController,
			)
		},
	) { innerPadding ->
		NavHost(
			navController = navController,
			startDestination = mainScreens.first().route,
			modifier = Modifier.padding(innerPadding),
		) {
			mainScreens.forEach { screen ->
				val (route, content) = screen
				composable(route) { content(navController) }
			}
		}
	}
}

@Composable
fun MainTopAppBar() {
	Surface(
		tonalElevation = 3.dp,
	) {
		CenterAlignedTopAppBar(
			title = { Text("Correios Tracker") },
		)
	}
}

@Composable
fun MainExtendedFloatingActionButton(
	navController: NavController,
) {
	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val isInTransitScreen = navBackStackEntry?.isInHierarchy(MainScreen.InTransit.route) == true
	
	val density = LocalDensity.current
	val padding = with(density) { 16.dp.roundToPx() }
	val offsetX = { width: Int -> width + padding }
	
	AnimatedVisibility(
		visible = isInTransitScreen,
		enter = slideInHorizontally(initialOffsetX = offsetX),
		exit = slideOutHorizontally(targetOffsetX = offsetX),
	) {
		ExtendedFloatingActionButton(
			text = { Text(text = stringResource(id = R.string.inTransitAdd)) },
			icon = { Icon(Icons.Filled.Add, null) },
			onClick = {},
		)
	}
}

@Composable
fun MainNavigationBar(
	navController: NavController,
) {
	NavigationBar {
		val navBackStackEntry by navController.currentBackStackEntryAsState()
		
		mainScreens.forEach { screen ->
			val (route, _, itemDescriptor) = screen
			val (iconActive, iconInactive, label) = itemDescriptor
			
			val isActive = navBackStackEntry?.isInHierarchy(screen.route) == true
			val setActive = {
				navController.navigate(route) {
					popUpTo(navController.graph.findStartDestination().id) { saveState = true }
					launchSingleTop = true
					restoreState = true
				}
			}
			
			NavigationBarItem(
				icon = { Icon(if (isActive) iconActive else iconInactive, null) },
				label = { Text(stringResource(label)) },
				selected = isActive,
				onClick = setActive,
			)
		}
	}
}

@Preview
@Composable
fun PreviewMainScreen() {
	CorreiosTrackerTheme {
		MainScreen()
	}
}