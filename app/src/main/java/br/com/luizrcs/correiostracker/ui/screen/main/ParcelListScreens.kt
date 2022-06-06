package br.com.luizrcs.correiostracker.ui.screen.main

import androidx.annotation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.vector.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import br.com.luizrcs.correiostracker.ui.theme.*

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
			contentDescription = null,
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