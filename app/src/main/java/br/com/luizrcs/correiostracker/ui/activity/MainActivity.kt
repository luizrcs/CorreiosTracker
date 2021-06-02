package br.com.luizrcs.correiostracker.ui.activity

import android.os.*
import android.text.*
import android.util.*
import androidx.appcompat.app.*
import androidx.core.content.res.*
import androidx.navigation.fragment.*
import androidx.navigation.ui.*
import br.com.luizrcs.correiostracker.R
import br.com.luizrcs.correiostracker.databinding.*
import com.google.android.material.dialog.*
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