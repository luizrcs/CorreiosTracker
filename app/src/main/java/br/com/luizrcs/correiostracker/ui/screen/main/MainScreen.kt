package br.com.luizrcs.correiostracker.ui.screen.main

import android.annotation.*
import android.content.res.*
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.*
import androidx.navigation.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import br.com.luizrcs.correiostracker.R
import br.com.luizrcs.correiostracker.repository.*
import br.com.luizrcs.correiostracker.ui.screen.*
import br.com.luizrcs.correiostracker.ui.theme.*
import br.com.luizrcs.correiostracker.ui.util.extensions.*
import br.com.luizrcs.correiostracker.ui.viewmodel.*

sealed class MainScreen(
	route: String,
	// content: @Composable (NavController) -> Unit,
	navigationBarItemDescriptor: NavigationBarItemDescriptor,
): NavigationBarScreen(
	route,
	// content,
	navigationBarItemDescriptor
) {
	object InTransit: MainScreen(
		route = "inTransit",
		navigationBarItemDescriptor = NavigationBarItemDescriptor(
			iconActive = Icons.Filled.LocalShipping,
			iconInactive = Icons.Outlined.LocalShipping,
			label = R.string.mainNavigationBarItemInTransit
		),
		// content = { navController -> InTransitScreen(navController = navController) }
	)
	
	object Finished: MainScreen(
		route = "finished",
		navigationBarItemDescriptor = NavigationBarItemDescriptor(
			iconActive = Icons.Filled.CheckCircle,
			iconInactive = Icons.Outlined.CheckCircle,
			label = R.string.mainNavigationBarItemFinished
		),
		// content = { navController -> FinishedScreen(navController = navController) }
	)
	
	object Tools: MainScreen(
		route = "tools",
		navigationBarItemDescriptor = NavigationBarItemDescriptor(
			iconActive = Icons.Filled.Handyman,
			iconInactive = Icons.Outlined.Handyman,
			label = R.string.mainNavigationBarItemTools
		),
		// content = { navController -> ToolsScreen(navController = navController) }
	)
}

val mainScreens = listOf(
	MainScreen.InTransit,
	MainScreen.Finished,
	MainScreen.Tools,
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: MainViewModel) {
	val navController = rememberNavController()
	
	val parcels by viewModel.filteredParcels.observeAsState(listOf())
	var openDialog by remember { mutableStateOf(false) }
	
	MainScaffold(
		navController = navController,
		parcels = parcels,
		onMainExtendedFabClick = { openDialog = true },
	)
	
	AddParcelDialog(
		openDialog = openDialog,
		onDismissRequest = { openDialog = false },
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
	navController: NavHostController,
	parcels: List<Parcel>,
	onMainExtendedFabClick: () -> Unit,
) {
	val colorScheme = correiosTrackerColorScheme()
	
	Scaffold(
		topBar = { MainTopAppBar() },
		floatingActionButton = {
			MainExtendedFloatingActionButton(
				navController = navController,
				onClick = onMainExtendedFabClick,
			)
		},
		bottomBar = { MainNavigationBar(navController = navController) },
		containerColor = colorScheme.background,
	) { innerPadding ->
		NavHost(
			navController = navController,
			startDestination = mainScreens.first().route,
			modifier = Modifier.padding(innerPadding),
		) {
			// Disabled while the error "androidx.compose.runtime.internal.ComposableLambdaImpl cannot be cast to class" persists
			/* mainScreens.forEach { screen ->
				val (route, content) = screen
				composable(route) { content(navController) }
			} */
			
			// Alternative declaration
			composable(MainScreen.InTransit.route) {
				InTransitScreen(
					navController = navController,
					parcels = parcels,
				)
			}
			
			composable(MainScreen.Finished.route) {
				FinishedScreen(
					navController = navController,
					parcels = parcels,
				)
			}
			
			composable(MainScreen.Tools.route) {}
		}
	}
}

@Composable
fun MainTopAppBar() {
	val useDynamicColors = useDynamicColors()
	val colorScheme = correiosTrackerColorScheme()
	
	Surface(
		tonalElevation = if (useDynamicColors) 3.dp else 0.dp,
	) {
		val topAppBarColors =
			if (useDynamicColors) TopAppBarDefaults.centerAlignedTopAppBarColors()
			else TopAppBarDefaults.centerAlignedTopAppBarColors(
				containerColor = colorScheme.secondaryContainer,
				scrolledContainerColor = colorScheme.secondaryContainer,
				navigationIconContentColor = colorScheme.onSecondaryContainer,
				titleContentColor = colorScheme.onSecondaryContainer,
				actionIconContentColor = colorScheme.onSecondaryContainer,
			)
		
		CenterAlignedTopAppBar(
			title = { Text("Correios Tracker") },
			colors = topAppBarColors,
		)
	}
}

@Composable
fun MainExtendedFloatingActionButton(
	navController: NavController,
	onClick: () -> Unit,
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
			text = { Text(text = stringResource(id = R.string.mainExtendedFloatingActionButtonAdd)) },
			icon = { Icon(Icons.Filled.Add) },
			onClick = onClick,
			// containerColor = correiosTrackerColorScheme().primary,
			// contentColor = correiosTrackerColorScheme().onPrimary,
		)
	}
}

@Composable
fun MainNavigationBar(
	navController: NavController,
) {
	val useDynamicColors = useDynamicColors()
	val colorScheme = correiosTrackerColorScheme()
	
	val containerColor = if (useDynamicColors) colorScheme.surface else colorScheme.secondaryContainer
	val contentColor = if (useDynamicColors) colorScheme.onSurface else colorScheme.onSecondaryContainer
	
	NavigationBar(
		containerColor = containerColor,
		contentColor = contentColor,
		tonalElevation = if (useDynamicColors) 3.dp else 0.dp,
	) {
		val navBackStackEntry by navController.currentBackStackEntryAsState()
		
		mainScreens.forEach { screen ->
			// val (route, _, itemDescriptor) = screen
			val (route, itemDescriptor) = screen
			val (iconActive, iconInactive, label) = itemDescriptor
			
			val isActive = navBackStackEntry?.isInHierarchy(screen.route) == true
			val setActive = {
				navController.navigate(route) {
					popUpTo(navController.graph.findStartDestination().id) { saveState = true }
					launchSingleTop = true
					restoreState = true
				}
			}
			
			val navigationBarItemColors =
				if (useDynamicColors) NavigationBarItemDefaults.colors()
				else NavigationBarItemDefaults.colors(
					indicatorColor = contentColor,
					selectedIconColor = containerColor,
					selectedTextColor = contentColor,
					unselectedIconColor = colorScheme.onTertiaryContainer,
					unselectedTextColor = colorScheme.onTertiaryContainer,
				)
			
			NavigationBarItem(
				icon = { Icon(if (isActive) iconActive else iconInactive) },
				label = { Text(stringResource(label)) },
				selected = isActive,
				onClick = setActive,
				colors = navigationBarItemColors,
			)
		}
	}
}

@Composable
fun AddParcelDialog(openDialog: Boolean, onDismissRequest: () -> Unit) {
	if (openDialog) {
		val useDynamicColors = useDynamicColors()
		val colorScheme = correiosTrackerColorScheme()
		
		Dialog(
			onDismissRequest = onDismissRequest,
			properties = DialogProperties(
				dismissOnBackPress = true,
				dismissOnClickOutside = true,
			),
		) {
			Surface(
				shape = RoundedCornerShape(16.dp),
				shadowElevation = 4.dp,
			) {
				Column(
					modifier = Modifier.padding(16.dp),
					verticalArrangement = Arrangement.spacedBy(8.dp)
				) {
					var name by remember { mutableStateOf("") }
					var code by remember { mutableStateOf("") }
					
					Text(
						text = stringResource(R.string.dialogAddParcelTitle),
						fontSize = 20.sp,
						style = CorreiosTrackerTypography.titleLarge,
					)
					
					val textFieldColors = if (useDynamicColors) TextFieldDefaults.textFieldColors(
						containerColor = Color.Transparent,
					) else TextFieldDefaults.textFieldColors(
						containerColor = Color.Transparent,
						cursorColor = colorScheme.onSurface,
						focusedLeadingIconColor = colorScheme.onSurface,
						focusedLabelColor = colorScheme.onSurface,
						unfocusedLeadingIconColor = colorScheme.onSurfaceVariant,
						unfocusedLabelColor = colorScheme.onSurfaceVariant,
					)
					
					TextField(
						value = name,
						onValueChange = { name = it },
						label = { Text(stringResource(R.string.dialogAddParcelName)) },
						leadingIcon = { Icon(Icons.Outlined.Edit) },
						colors = textFieldColors,
					)
					TextField(
						value = code,
						onValueChange = { code = it },
						label = { Text(stringResource(R.string.dialogAddParcelCode)) },
						leadingIcon = { Icon(Icons.Outlined.QrCode) },
						colors = textFieldColors,
					)
					
					Row(modifier = Modifier.align(Alignment.End)) {
						TextButton(onClick = onDismissRequest) { Text(stringResource(R.string.dialogAddParcelCancel)) }
						Spacer(modifier = Modifier.width(8.dp))
						Button(onClick = {}) { Text(stringResource(R.string.dialogAddParcelAdd)) }
					}
				}
			}
		}
	}
}

@Preview(
	name = "Light Mode",
	apiLevel = 32,
	showSystemUi = true,
	uiMode = Configuration.UI_MODE_NIGHT_NO,
	device = Devices.PIXEL_4,
)
@Preview(
	name = "Dark Mode",
	apiLevel = 32,
	showSystemUi = true,
	uiMode = Configuration.UI_MODE_NIGHT_YES,
	device = Devices.PIXEL_4,
)
@Composable
fun PreviewMainScreen() {
	val navController = rememberNavController()
	
	CorreiosTrackerTheme {
		MainScaffold(
			navController = navController,
			parcels = listOf(),
			onMainExtendedFabClick = {},
		)
	}
}

@Preview(
	name = "Light Mode",
	showBackground = true,
	backgroundColor = 0xffffffff,
	uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
	name = "Dark Mode",
	showBackground = true,
	backgroundColor = 0xff000000,
	uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun PreviewAddParcelDialog() {
	CorreiosTrackerTheme {
		AddParcelDialog(
			openDialog = true,
			onDismissRequest = {},
		)
	}
}