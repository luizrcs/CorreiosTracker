package br.com.luizrcs.correiostracker.ui.fragment

import android.os.*
import android.view.*
import android.widget.*
import androidx.fragment.app.*
import br.com.luizrcs.correiostracker.*
import br.com.luizrcs.correiostracker.ui.activity.*

open class CustomFragment: Fragment() {
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		val toolbarTextSwitcher = (activity as MainActivity).binding.toolbarTextSwitcher
		val appName = getString(R.string.app_name)
		if ((toolbarTextSwitcher.currentView as TextView).text != appName) toolbarTextSwitcher.setText(appName)
	}
}