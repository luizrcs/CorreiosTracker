package br.com.luizrcs.correiostracker.ui.activity

import android.os.*
import android.view.*
import androidx.activity.*
import androidx.activity.compose.*
import androidx.core.view.*
import br.com.luizrcs.correiostracker.ui.screen.main.*
import br.com.luizrcs.correiostracker.ui.theme.*
import br.com.luizrcs.correiostracker.ui.viewmodel.*
import com.google.android.material.appbar.*
import com.google.android.material.bottomnavigation.*
import com.google.android.material.floatingactionbutton.*
import dagger.hilt.android.*

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
	
	val viewModel by viewModels<MainViewModel>()
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
		setContent {
			CorreiosTrackerTheme {
				MainScreen(viewModel)
			}
		}
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