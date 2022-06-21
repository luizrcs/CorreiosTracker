package br.com.luizrcs.correiostracker.ui.screen.main

import android.content.res.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.*
import androidx.navigation.*
import androidx.navigation.compose.*
import br.com.luizrcs.correiostracker.R
import br.com.luizrcs.correiostracker.repository.*
import br.com.luizrcs.correiostracker.ui.theme.*
import br.com.luizrcs.correiostracker.ui.util.*

@Composable
fun InTransitScreen(
	navController: NavController,
	parcels: List<Parcel>,
	onParcelLongPress: (Parcel) -> Unit = {},
) {
	val parcels = generateTestParcels()
	
	if (parcels.isEmpty()) {
		NoParcels(
			icon = Icons.Outlined.ListAlt,
			message = R.string.inTransitNoParcels,
		)
	} else {
		ParcelList(
			navController = navController,
			parcels = parcels,
			onParcelLongPress = onParcelLongPress,
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
fun PreviewInTransitScreen() {
	val navController = rememberNavController()
	
	CorreiosTrackerTheme {
		InTransitScreen(
			navController = navController,
			parcels = generateTestParcels(),
		)
	}
}