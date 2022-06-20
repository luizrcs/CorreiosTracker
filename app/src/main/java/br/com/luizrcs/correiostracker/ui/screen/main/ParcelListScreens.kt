package br.com.luizrcs.correiostracker.ui.screen.main

import android.content.res.*
import androidx.annotation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import androidx.constraintlayout.compose.*
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.*
import androidx.navigation.compose.*
import br.com.luizrcs.correiostracker.R
import br.com.luizrcs.correiostracker.repository.*
import br.com.luizrcs.correiostracker.ui.theme.*
import br.com.luizrcs.correiostracker.ui.util.*
import br.com.luizrcs.correiostracker.ui.util.extensions.*
import com.google.accompanist.swiperefresh.*
import kotlin.OptIn

@Composable
fun NoParcels(
	icon: ImageVector,
	@StringRes message: Int,
) {
	Column(
		modifier = Modifier.fillMaxSize(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		val outlineColor = correiosTrackerColorScheme().outline
		
		Icon(
			imageVector = icon,
			modifier = Modifier.size(120.dp),
			tint = outlineColor,
		)
		Text(
			text = stringResource(id = message),
			color = outlineColor,
			fontSize = 20.sp,
			textAlign = TextAlign.Center,
		)
	}
}

@Composable
fun ParcelList(
	navController: NavController,
	parcels: List<Parcel>,
	onRefresh: () -> Unit = {},
) {
	val isRefreshing by remember { mutableStateOf(false) }
	
	SwipeRefresh(
		state = rememberSwipeRefreshState(isRefreshing),
		onRefresh = onRefresh,
	) {
		LazyColumn(
			contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
			verticalArrangement = Arrangement.spacedBy(16.dp),
		) {
			items(
				items = parcels,
				key = { parcel -> parcel.trackingCode },
			) { parcel ->
				Parcel(
					navController = navController,
					parcel = parcel,
				)
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Parcel(
	navController: NavController,
	parcel: Parcel,
) {
	val (name, code, _, type) = parcel
	
	val useDynamicColors = useDynamicColors()
	val colorScheme = correiosTrackerColorScheme()
	
	val cardColors =
		if (useDynamicColors) CardDefaults.cardColors()
		else CardDefaults.cardColors(
			containerColor = colorScheme.surfaceVariant,
			contentColor = colorScheme.onSurfaceVariant,
		)
	
	Card(
		onClick = {},
		modifier = Modifier.shadow(
			elevation = 1.dp,
			shape = RoundedCornerShape(12.dp),
			clip = false,
		),
		enabled = true,
		colors = cardColors,
	) {
		ConstraintLayout(
			modifier = Modifier
				.fillMaxWidth()
				.padding(16.dp),
		) {
			val (
				iconRef,
				nameRef,
				statusRef,
				locationRef,
				typeRef,
				dateTimeRef,
			) = createRefs()
			
			Box(
				modifier = Modifier
					.size(48.dp)
					.clip(CircleShape)
					.background(Color.Gray)
					.constrainAs(iconRef) {
						top.linkTo(parent.top)
						bottom.linkTo(parent.bottom)
					},
				contentAlignment = Alignment.Center,
			) {
				Icon(
					imageVector = Icons.Outlined.LocalShipping,
					modifier = Modifier.size(28.dp),
					tint = Color.White,
				)
			}
			
			Text(
				text = name.repeat(10),
				style = CorreiosTrackerTypography.titleLarge,
				fontSize = 20.sp,
				fontWeight = FontWeight.SemiBold,
				overflow = TextOverflow.Ellipsis,
				maxLines = 1,
				modifier = Modifier.constrainAs(nameRef) {
					linkTo(
						start = iconRef.end,
						startMargin = 16.dp,
						end = typeRef.start,
						endMargin = 16.dp,
						bias = 0f,
					)
					width = Dimension.fillToConstraints
				},
			)
			
			Text(
				text = parcel.parcelEvents?.first()?.description ?: "Objeto não encontrado",
				style = CorreiosTrackerTypography.bodyLarge,
				fontWeight = FontWeight.Medium,
				overflow = TextOverflow.Ellipsis,
				maxLines = 1,
				modifier = Modifier.constrainAs(statusRef) {
					linkTo(
						start = nameRef.start,
						end = dateTimeRef.start,
						endMargin = 16.dp,
						bias = 0f,
					)
					width = Dimension.fillToConstraints
					top.linkTo(nameRef.bottom, margin = 4.dp)
				},
			)
			
			Row(
				modifier = Modifier.constrainAs(locationRef) {
					linkTo(
						start = nameRef.start,
						end = dateTimeRef.start,
						endMargin = 8.dp,
						bias = 0f,
					)
					width = Dimension.fillToConstraints
					top.linkTo(statusRef.bottom, margin = 4.dp)
				},
				verticalAlignment = Alignment.CenterVertically,
			) {
				Icon(
					imageVector = Icons.Outlined.Place,
					modifier = Modifier.size(16.dp),
					tint = Color.Red,
				)
				Spacer(modifier = Modifier.width(4.dp))
				Text(
					text = "Somewhere",
					style = CorreiosTrackerTypography.bodyMedium,
					overflow = TextOverflow.Ellipsis,
					maxLines = 1,
				)
			}
			
			Row(
				modifier = Modifier.constrainAs(typeRef) {
					end.linkTo(parent.end)
				},
				horizontalArrangement = Arrangement.spacedBy(16.dp),
				verticalAlignment = Alignment.CenterVertically,
			) {
				Text(
					text = "CATEGORY",
					fontSize = 12.sp,
					modifier = Modifier
						.border(
							width = 1.dp,
							color = colorScheme.onSurfaceVariant,
							shape = RoundedCornerShape(4.dp),
						)
						.padding(4.dp, 1.dp, 4.dp, 2.dp)
				)
				
				Image(painterResource(id = R.drawable.br), Modifier.size(24.dp))
			}
			
			Column(
				modifier = Modifier.constrainAs(dateTimeRef) {
					end.linkTo(parent.end)
					bottom.linkTo(parent.bottom)
				},
				horizontalAlignment = Alignment.End,
			) {
				Text(
					text = "01/01",
					style = CorreiosTrackerTypography.bodyMedium,
				)
				Text(
					text = "00:00",
					style = CorreiosTrackerTypography.bodyMedium,
				)
			}
		}
	}
}

@Preview(
	name = "Light Mode",
	showBackground = true,
	backgroundColor = 0xffffffff,
	showSystemUi = true,
	uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
	name = "Dark Mode",
	showBackground = true,
	backgroundColor = 0xff000000,
	showSystemUi = true,
	uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun PreviewParcelList() {
	val navController = rememberNavController()
	
	CorreiosTrackerTheme {
		ParcelList(
			navController = navController,
			parcels = generateTestParcels(),
		)
	}
}