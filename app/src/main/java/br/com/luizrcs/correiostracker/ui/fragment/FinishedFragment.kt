package br.com.luizrcs.correiostracker.ui.fragment

import android.os.*
import android.view.*
import androidx.fragment.app.*
import br.com.luizrcs.correiostracker.databinding.*
import br.com.luizrcs.correiostracker.ui.viewmodel.*

class FinishedFragment: Fragment() {
	
	private var _binding: FragmentFinishedBinding? = null
	private val binding get() = _binding!!
	
	val viewModel by viewModels<InTransitViewModel>()
	
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		_binding = FragmentFinishedBinding.inflate(inflater, container, false)
		
		return binding.root
	}
}