package br.com.luizrcs.correiostracker.ui.activity

import android.os.*
import android.view.*
import androidx.appcompat.app.*
import androidx.core.view.*
import androidx.navigation.fragment.*
import androidx.navigation.ui.*
import br.com.luizrcs.correiostracker.R
import br.com.luizrcs.correiostracker.databinding.*
import com.google.android.material.appbar.*
import com.google.android.material.bottomnavigation.*
import com.google.android.material.floatingactionbutton.*
import dagger.hilt.android.*

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
	
	private lateinit var _binding: ActivityMainBinding
	val binding by ::_binding
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
		_binding = ActivityMainBinding.inflate(layoutInflater)
		
		setContentView(binding.root)
		
		val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
		binding.bottomNavigation.setupWithNavController(navHostFragment.navController)
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