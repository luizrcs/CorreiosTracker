package br.com.luizrcs.correiostracker.ui.screen.main

import android.annotation.*
import android.content.res.*
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.text.*
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
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.*
import androidx.constraintlayout.compose.*
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
	
	var parcelDialogEdit by remember { mutableStateOf(false) }
	var openParcelDialog by remember { mutableStateOf(false) }
	
	MainScaffold(
		navController = navController,
		parcels = parcels,
		onMainExtendedFabClick = { parcelDialogEdit = false; openParcelDialog = true },
		onParcelLongPress = { parcelDialogEdit = true; openParcelDialog = true },
	)
	
	ParcelDialog(
		editParcel = parcelDialogEdit,
		openDialog = openParcelDialog,
		onDismissRequest = { openParcelDialog = false },
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
	navController: NavHostController,
	parcels: List<Parcel>,
	onMainExtendedFabClick: () -> Unit = {},
	onParcelLongPress: (Parcel) -> Unit = {},
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
					onParcelLongPress = onParcelLongPress,
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
fun ParcelDialog(
	editParcel: Boolean,
	openDialog: Boolean,
	onDismissRequest: () -> Unit = {},
) {
	if (openDialog) {
		val useDynamicColors = useDynamicColors()
		val darkTheme = isSystemInDarkTheme()
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
				ConstraintLayout(
					modifier = Modifier.padding(16.dp),
				) {
					val (titleRef, nameRef, codeRef, copyRef, cancelRef, doneRef) = createRefs()
					
					val focusManager = LocalFocusManager.current
					
					var name by remember { mutableStateOf("") }
					var code by remember { mutableStateOf("") }
					
					// Title
					Text(
						text = stringResource(if (editParcel) R.string.dialogParcelTitleEdit else R.string.dialogParcelTitleAdd),
						fontSize = 20.sp,
						style = CorreiosTrackerTypography.titleLarge,
						modifier = Modifier.constrainAs(titleRef) {},
					)
					
					val contentColor = if (!darkTheme) colorScheme.secondary else colorScheme.onSurface
					
					val textFieldColors = if (useDynamicColors) TextFieldDefaults.textFieldColors(
						containerColor = Color.Transparent,
					) else TextFieldDefaults.textFieldColors(
						containerColor = Color.Transparent,
						cursorColor = contentColor,
						focusedLeadingIconColor = contentColor,
						focusedLabelColor = contentColor,
						unfocusedLeadingIconColor = colorScheme.onSurfaceVariant,
						unfocusedLabelColor = colorScheme.onSurfaceVariant,
					)
					
					val keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
					
					// Name field
					TextField(
						value = name,
						onValueChange = { name = it },
						label = { Text(stringResource(R.string.dialogParcelName)) },
						leadingIcon = { Icon(Icons.Outlined.Edit) },
						keyboardOptions = KeyboardOptions(imeAction = if (editParcel) ImeAction.Done else ImeAction.Next),
						keyboardActions = keyboardActions,
						singleLine = true,
						colors = textFieldColors,
						modifier = Modifier.constrainAs(nameRef) {
							top.linkTo(titleRef.bottom, margin = 8.dp)
						},
					)
					
					val iconSize = 24.dp
					
					// Code field
					TextField(
						value = code,
						onValueChange = { code = it },
						enabled = !editParcel,
						label = {
							Text(
								text = stringResource(R.string.dialogParcelCode),
								overflow = TextOverflow.Ellipsis,
								maxLines = 1,
							)
						},
						leadingIcon = { Icon(Icons.Outlined.QrCode) },
						keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
						keyboardActions = keyboardActions,
						singleLine = true,
						colors = textFieldColors,
						modifier = Modifier
							.padding(end = if (editParcel) 24.dp + iconSize else 0.dp)
							.constrainAs(codeRef) {
								end.linkTo(nameRef.end)
								top.linkTo(nameRef.bottom, margin = 8.dp)
							},
					)
					
					// Copy button
					if (editParcel) {
						IconButton(
							onClick = {},
							modifier = Modifier.constrainAs(copyRef) {
								end.linkTo(parent.end)
								top.linkTo(codeRef.top)
								bottom.linkTo(codeRef.bottom)
							},
						) {
							Icon(
								imageVector = Icons.Outlined.ContentCopy,
								modifier = Modifier.size(iconSize),
							)
						}
					}
					
					// Cancel button
					TextButton(
						onClick = onDismissRequest,
						modifier = Modifier.constrainAs(cancelRef) {
							top.linkTo(doneRef.top)
							end.linkTo(doneRef.start, margin = 8.dp)
						},
					) {
						Text(stringResource(R.string.dialogParcelCancel))
					}
					
					// Done button
					Button(
						onClick = {},
						modifier = Modifier.constrainAs(doneRef) {
							top.linkTo(codeRef.bottom, margin = 16.dp)
							end.linkTo(parent.end)
						},
					) {
						Text(stringResource(if (editParcel) R.string.dialogParcelSave else R.string.dialogParcelAdd))
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
		ParcelDialog(
			editParcel = false,
			openDialog = true,
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
fun PreviewEditParcelDialog() {
	CorreiosTrackerTheme {
		ParcelDialog(
			editParcel = true,
			openDialog = true,
		)
	}
}