package br.com.luizrcs.correiostracker.ui.screen.main

import android.annotation.*
import android.content.res.*
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.text.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
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
import br.com.luizrcs.correiostracker.ui.util.*
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
	data object InTransit: MainScreen(
		route = "inTransit",
		navigationBarItemDescriptor = NavigationBarItemDescriptor(
			iconActive = R.drawable.local_shipping_filled_24,
			iconInactive = R.drawable.local_shipping_outlined_24,
			label = R.string.mainNavigationBarItemInTransit
		),
		// content = { navController -> InTransitScreen(navController = navController) }
	)
	
	data object Finished: MainScreen(
		route = "finished",
		navigationBarItemDescriptor = NavigationBarItemDescriptor(
			iconActive = R.drawable.check_circle_filled_24,
			iconInactive = R.drawable.check_circle_outlined_24,
			label = R.string.mainNavigationBarItemFinished
		),
		// content = { navController -> FinishedScreen(navController = navController) }
	)
	
	data object Tools: MainScreen(
		route = "tools",
		navigationBarItemDescriptor = NavigationBarItemDescriptor(
			iconActive = R.drawable.handyman_filled_24,
			iconInactive = R.drawable.handyman_outlined_24,
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
	
	var selectedParcel by remember { mutableStateOf<Parcel?>(null) }
	var openParcelDialog by remember { mutableStateOf(false) }
	
	MainScaffold(
		navController = navController,
		parcels = parcels,
		onMainExtendedFabClick = { selectedParcel = null; openParcelDialog = true },
		onParcelLongPress = { selectedParcel = it; openParcelDialog = true },
	)
	
	MainParcelDialog(
		parcel = selectedParcel,
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

@OptIn(ExperimentalMaterial3Api::class)
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
		val mainExtendedFloatingActionButtonAdd = stringResource(R.string.mainExtendedFloatingActionButtonAdd)
		
		ExtendedFloatingActionButton(
			text = { Text(mainExtendedFloatingActionButtonAdd) },
			icon = { Icon(painterResource(R.drawable.add_outlined_24), contentDescription = mainExtendedFloatingActionButtonAdd) },
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
			val (iconActive, iconInactive, labelResource) = itemDescriptor
			
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
			
			val label = stringResource(labelResource)
			
			NavigationBarItem(
				icon = {
					Icon(
						painterResource(if (isActive) iconActive else iconInactive),
						contentDescription = label,
					)
				},
				label = { Text(label) },
				selected = isActive,
				onClick = setActive,
				colors = navigationBarItemColors,
			)
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainParcelDialog(
	openDialog: Boolean,
	parcel: Parcel? = null,
	onDismissRequest: () -> Unit = {},
	onDelete: () -> Unit = {},
	onDone: (String, String) -> Unit = { _, _ -> },
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
					val (
						titleRef,
						deleteRef,
						nameRef,
						codeRef,
						copyRef,
						cancelRef,
						doneRef,
					) = createRefs()
					
					val context = LocalContext.current
					val clipboardManager = LocalClipboardManager.current
					val focusManager = LocalFocusManager.current
					
					val editParcel = parcel != null
					
					var name by remember { mutableStateOf(parcel?.name ?: "") }
					var nameValid by remember { mutableStateOf(true) }
					var nameError by remember { mutableStateOf<String?>(null) }
					
					val setNameError = { error: String? -> nameError = error; nameValid = error == null }
					
					var code by remember { mutableStateOf(parcel?.trackingCode ?: "") }
					var codeValid by remember { mutableStateOf(true) }
					var codeError by remember { mutableStateOf<String?>(null) }
					
					val setCodeError = { error: String? -> codeError = error; codeValid = error == null }
					val codeEmptyError = stringResource(R.string.dialogParcelCodeEmpty)
					val codeInvalidError = stringResource(R.string.dialogParcelCodeInvalid)
					
					val validate = {
						name = name.trim()
						code = code.trim()
						
						nameValid = true
						codeValid = true
						
						if (!editParcel) {
							if (code.isEmpty()) setCodeError(codeEmptyError)
							else if (!code.matches(Parcel.trackingCodeRegex)) setCodeError(codeInvalidError)
						}
						
						nameValid && codeValid
					}
					
					// Title
					Text(
						text = stringResource(if (editParcel) R.string.dialogParcelTitleEdit else R.string.dialogParcelTitleAdd),
						fontSize = 20.sp,
						style = CorreiosTrackerTypography.titleLarge,
						modifier = Modifier.constrainAs(titleRef) {},
					)
					
					val iconSize = 24.dp
					
					// Delete button
					if (editParcel) {
						IconButton(
							onClick = onDelete,
							modifier = Modifier.constrainAs(deleteRef) {
								linkTo(
									top = titleRef.top,
									topMargin = 4.dp,
									bottom = titleRef.bottom,
									start = titleRef.end,
									end = parent.end,
									horizontalBias = 1f,
								)
							},
						) {
							Icon(
								painterResource(R.drawable.delete_outlined_24),
								contentDescription = null,
								modifier = Modifier.size(iconSize),
							)
						}
					}
					
					val contentColor = if (!darkTheme) colorScheme.secondary else colorScheme.onSurface
					
					val textFieldColors = if (useDynamicColors) TextFieldDefaults.colors(
						focusedContainerColor = Color.Transparent,
						unfocusedContainerColor = Color.Transparent,
						disabledContainerColor = Color.Transparent,
					) else TextFieldDefaults.colors(
						focusedContainerColor = Color.Transparent,
						unfocusedContainerColor = Color.Transparent,
						disabledContainerColor = Color.Transparent,
						cursorColor = contentColor,
						focusedLeadingIconColor = contentColor,
						unfocusedLeadingIconColor = colorScheme.onSurfaceVariant,
						errorLeadingIconColor = colorScheme.error,
						focusedLabelColor = contentColor,
						unfocusedLabelColor = colorScheme.onSurfaceVariant,
					)
					
					val keyboardActions = KeyboardActions(
						onDone = {
							focusManager.clearFocus()
							validate()
						}
					)
					
					// Name field
					Column(
						modifier = Modifier.constrainAs(nameRef) {
							top.linkTo(titleRef.bottom, margin = 8.dp)
						},
					) {
						TextField(
							value = name,
							onValueChange = { name = it },
							label = { Text(stringResource(R.string.dialogParcelName)) },
							leadingIcon = { Icon(painterResource(R.drawable.edit_outlined_24), contentDescription = null) },
							keyboardOptions = KeyboardOptions(imeAction = if (editParcel) ImeAction.Done else ImeAction.Next),
							keyboardActions = keyboardActions,
							singleLine = true,
							colors = textFieldColors,
						)
						
						AnimatedVisibility(
							visible = !nameValid,
						) {
							Row(
								verticalAlignment = Alignment.CenterVertically,
								modifier = Modifier.padding(top = 4.dp),
							) {
								Icon(
									painterResource(R.drawable.error_filled_24),
									contentDescription = null,
									modifier = Modifier.size(16.dp),
									tint = colorScheme.error,
								)
								
								Text(
									text = nameError ?: "",
									color = colorScheme.error,
									style = CorreiosTrackerTypography.labelMedium,
									modifier = Modifier.padding(start = 8.dp),
								)
							}
						}
					}
					
					// Code field
					Column(
						modifier = Modifier.constrainAs(codeRef) {
							top.linkTo(nameRef.bottom, margin = 8.dp)
							end.linkTo(nameRef.end)
						},
					) {
						TextField(
							value = code,
							onValueChange = { code = it; codeValid = true },
							enabled = !editParcel,
							isError = !codeValid,
							label = {
								Text(
									text = stringResource(R.string.dialogParcelCode),
									overflow = TextOverflow.Ellipsis,
									maxLines = 1,
								)
							},
							leadingIcon = { Icon(painterResource(R.drawable.qr_code_outlined_24), contentDescription = null) },
							keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
							keyboardActions = keyboardActions,
							singleLine = true,
							colors = textFieldColors,
							modifier = Modifier.padding(end = if (editParcel) 24.dp + iconSize else 0.dp),
						)
						
						AnimatedVisibility(
							visible = !codeValid,
						) {
							Row(
								verticalAlignment = Alignment.CenterVertically,
								modifier = Modifier.padding(top = 4.dp),
							) {
								Icon(
									painterResource(R.drawable.error_filled_24),
									contentDescription = null,
									modifier = Modifier.size(16.dp),
									tint = colorScheme.error,
								)
								
								Text(
									text = codeError ?: "",
									color = colorScheme.error,
									style = CorreiosTrackerTypography.labelMedium,
									modifier = Modifier.padding(start = 8.dp),
								)
							}
						}
					}
					
					// Copy button
					if (editParcel) {
						val codeCopiedText = stringResource(R.string.dialogParcelCodeCopied)
						
						IconButton(
							onClick = {
								clipboardManager.setText(AnnotatedString(code))
								showShortToast(context, codeCopiedText)
							},
							modifier = Modifier.constrainAs(copyRef) {
								top.linkTo(codeRef.top)
								bottom.linkTo(codeRef.bottom)
								end.linkTo(parent.end)
							},
						) {
							Icon(
								painterResource(R.drawable.content_copy_outlined_24),
								contentDescription = null,
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
						onClick = { if (validate()) onDone(name, code) },
						modifier = Modifier.constrainAs(doneRef) {
							top.linkTo(codeRef.bottom, margin = 16.dp)
							bottom.linkTo(parent.bottom)
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
		MainParcelDialog(
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
		MainParcelDialog(
			openDialog = true,
			parcel = generateTestParcels().first(),
		)
	}
}