package br.com.luizrcs.correiostracker.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.*
import androidx.navigation.ui.*
import br.com.luizrcs.correiostracker.R
import dagger.hilt.android.*
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
		setContentView(R.layout.activity_main)
		
		val navHostController = fragmentContainer.findNavController()
		bottomNavigation.setupWithNavController(navHostController)
	}
}