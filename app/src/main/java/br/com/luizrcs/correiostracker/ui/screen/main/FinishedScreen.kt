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

@Composable
fun FinishedScreen(
	navController: NavController,
	parcels: List<Parcel>,
) {
	NoParcels(
		icon = Icons.Outlined.ErrorOutline,
		message = R.string.finishedNoParcels,
	)
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
fun PreviewFinishedScreen() {
	val navController = rememberNavController()
	
	CorreiosTrackerTheme {
		FinishedScreen(
			navController = navController,
			parcels = listOf(),
		)
	}
}