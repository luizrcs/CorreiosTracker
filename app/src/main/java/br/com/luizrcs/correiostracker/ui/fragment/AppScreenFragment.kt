package br.com.luizrcs.correiostracker.ui.fragment

import android.os.*
import android.view.*
import androidx.fragment.app.*
import br.com.luizrcs.correiostracker.*
import br.com.luizrcs.correiostracker.ui.activity.*
import br.com.luizrcs.correiostracker.ui.util.extensions.*

open class AppScreenFragment: Fragment() {
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		with(activity as MainActivity) {
			val color = getColorFromAttr(R.attr.colorPrimary)
			animateStatusBarColor(window.statusBarColor, color)
			animateNavigationBarColor(window.navigationBarColor, color)
			
			binding.toolbar.showAnimated()
			binding.bottomNavigation.showAnimated()
		}
	}
}