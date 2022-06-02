package br.com.luizrcs.correiostracker.ui.screen.main

import android.content.res.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import androidx.navigation.*
import androidx.navigation.compose.*
import br.com.luizrcs.correiostracker.R
import br.com.luizrcs.correiostracker.ui.theme.*

@Composable
fun InTransitScreen(navController: NavController) {
	Column(
		modifier = Modifier.fillMaxSize(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		val outlineColor = correiosTrackerColorScheme().outline
		
		Icon(
			imageVector = Icons.Outlined.ListAlt,
			contentDescription = null,
			modifier = Modifier.size(120.dp),
			tint = outlineColor,
		)
		Text(
			text = stringResource(id = R.string.in_transit_no_parcels),
			color = outlineColor,
			fontSize = 20.sp,
			textAlign = TextAlign.Center,
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
		)
	}
}