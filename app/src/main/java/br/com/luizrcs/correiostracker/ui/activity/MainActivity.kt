@file:OptIn(
	ExperimentalMaterial3Api::class
)

package br.com.luizrcs.correiostracker.ui.activity

import android.annotation.*
import android.os.*
import android.view.*
import androidx.activity.*
import androidx.activity.compose.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.*
import androidx.compose.ui.tooling.preview.*
import androidx.core.view.*
import br.com.luizrcs.correiostracker.databinding.*
import br.com.luizrcs.correiostracker.ui.theme.*
import com.google.android.material.appbar.*
import com.google.android.material.bottomnavigation.*
import com.google.android.material.floatingactionbutton.*
import dagger.hilt.android.*

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
	
	private lateinit var _binding: ActivityMainBinding
	val binding by ::_binding
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
		setContent {
			CorreiosTrackerTheme {
				MainScreen()
			}
		}
	}
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
	var selectedNavigationBarItem by remember { mutableStateOf(0) }
	
	Scaffold(
		topBar = { MainTopAppBar() },
		content = {},
		floatingActionButton = { MainExtendedFloatingActionButton() },
		bottomBar = {
			MainNavigationBar(
				selectedItem = selectedNavigationBarItem,
				onSelectItem = { selectedNavigationBarItem = it }
			)
		}
	)
}

@Composable
fun MainTopAppBar() {
	CenterAlignedTopAppBar(
		title = { Text("Correios Tracker") }
	)
}

@Composable
fun MainExtendedFloatingActionButton() {
	ExtendedFloatingActionButton(
		text = { Text(text = "Adicionar") },
		icon = { Icon(Icons.Filled.Add, null) },
		onClick = {}
	)
}

val mainNavigationBarItems = listOf(
	NavigationBarItemDescriptor(
		Icons.Filled.LocalShipping,
		Icons.Outlined.LocalShipping,
		"Em trânsito"
	),
	NavigationBarItemDescriptor(
		Icons.Filled.CheckCircle,
		Icons.Outlined.CheckCircle,
		"Finalizadas"
	),
	NavigationBarItemDescriptor(
		Icons.Filled.Handyman,
		Icons.Outlined.Handyman,
		"Ferramentas"
	)
)

data class NavigationBarItemDescriptor(
	val iconActive: ImageVector,
	val iconInactive: ImageVector,
	val label: String
)

@Composable
fun MainNavigationBar(selectedItem: Int, onSelectItem: (Int) -> Unit) {
	NavigationBar {
		mainNavigationBarItems.forEachIndexed { index, item ->
			val (iconActive, iconInactive, label) = item
			
			val isActive = selectedItem == index
			val setActive = { onSelectItem(index) }
			
			NavigationBarItem(
				icon = { Icon(if (isActive) iconActive else iconInactive, null) },
				label = { Text(label) },
				selected = isActive,
				onClick = setActive
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

fun MaterialToolbar.hideAnimated(endAction: ((MaterialToolbar) -> Unit)? = null) {
	if (isVisible) {
		animate()
			.setDuration(200L)
			.alpha(0.0f)
			.translationYBy(-height.toFloat())
			.withEndAction { visibility = View.GONE; endAction?.invoke(this) }
	}
}

fun MaterialToolbar.showAnimated(endAction: ((MaterialToolbar) -> Unit)? = null) {
	if (!isVisible) {
		visibility = View.VISIBLE
		animate()
			.setDuration(200L)
			.alpha(1.0f)
			.translationYBy(height.toFloat())
			.withEndAction { endAction?.invoke(this) }
	}
}

fun ExtendedFloatingActionButton.hideAnimated(endAction: ((ExtendedFloatingActionButton) -> Unit)? = null) {
	if (isVisible) {
		animate()
			.setDuration(200L)
			.alpha(0.0f)
			.translationXBy((width + marginEnd).toFloat())
			.withEndAction { visibility = View.GONE; endAction?.invoke(this) }
	}
}

fun ExtendedFloatingActionButton.showAnimated(endAction: ((ExtendedFloatingActionButton) -> Unit)? = null) {
	if (!isVisible) {
		visibility = View.VISIBLE
		animate()
			.setDuration(200L)
			.alpha(1.0f)
			.translationXBy(-(width + marginEnd).toFloat())
			.withEndAction { endAction?.invoke(this) }
	}
}

fun BottomNavigationView.hideAnimated(endAction: ((BottomNavigationView) -> Unit)? = null) {
	if (isVisible) {
		animate()
			.setDuration(200L)
			.alpha(0.0f)
			.translationYBy(height.toFloat())
			.withEndAction { visibility = View.GONE; endAction?.invoke(this) }
	}
}

fun BottomNavigationView.showAnimated(endAction: ((BottomNavigationView) -> Unit)? = null) {
	if (!isVisible) {
		visibility = View.VISIBLE
		animate()
			.setDuration(200L)
			.alpha(1.0f)
			.translationYBy(-height.toFloat())
			.withEndAction { endAction?.invoke(this) }
	}
}