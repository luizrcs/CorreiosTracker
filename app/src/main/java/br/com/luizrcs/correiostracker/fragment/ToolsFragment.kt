package br.com.luizrcs.correiostracker.fragment

import android.os.*
import android.view.*
import androidx.fragment.app.*
import br.com.luizrcs.correiostracker.*
import br.com.luizrcs.correiostracker.databinding.*
import br.com.luizrcs.correiostracker.viewmodel.*

class ToolsFragment: Fragment() {
	
	private var _binding: FragmentToolsBinding? = null
	private val binding get() = _binding!!
	
	val viewModel by viewModels<InTransitViewModel>()
	
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		_binding = FragmentToolsBinding.inflate(inflater, container, false)
		
		return binding.root
	}
}