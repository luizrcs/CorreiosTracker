package br.com.luizrcs.correiostracker.ui.util.extensions

import androidx.compose.foundation.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.*
import androidx.compose.ui.graphics.vector.*
import androidx.compose.ui.layout.*

@Composable
@NonRestartableComposable
fun Icon(
	imageVector: ImageVector,
	modifier: Modifier = Modifier,
	tint: Color = LocalContentColor.current,
) = Icon(
	imageVector = imageVector,
	contentDescription = null,
	modifier = modifier,
	tint = tint
)

@Composable
@NonRestartableComposable
fun Icon(
	painter: Painter,
	modifier: Modifier = Modifier,
	tint: Color = LocalContentColor.current,
) = Icon(
	painter = painter,
	contentDescription = null,
	modifier = modifier,
	tint = tint
)

@Composable
@NonRestartableComposable
fun Image(
	imageVector: ImageVector,
	modifier: Modifier = Modifier,
	alignment: Alignment = Alignment.Center,
	contentScale: ContentScale = ContentScale.Fit,
	alpha: Float = DefaultAlpha,
	colorFilter: ColorFilter? = null,
) = Image(
	imageVector = imageVector,
	contentDescription = null,
	modifier = modifier,
	alignment = alignment,
	contentScale = contentScale,
	alpha = alpha,
	colorFilter = colorFilter
)

@Composable
@NonRestartableComposable
fun Image(
	painter: Painter,
	modifier: Modifier = Modifier,
	alignment: Alignment = Alignment.Center,
	contentScale: ContentScale = ContentScale.Fit,
	alpha: Float = DefaultAlpha,
	colorFilter: ColorFilter? = null,
) = Image(
	painter = painter,
	contentDescription = null,
	modifier = modifier,
	alignment = alignment,
	contentScale = contentScale,
	alpha = alpha,
	colorFilter = colorFilter
)
