package br.com.luizrcs.correiostracker.fragment

import android.os.*
import android.view.*
import androidx.fragment.app.*
import androidx.lifecycle.*
import br.com.luizrcs.correiostracker.databinding.*
import br.com.luizrcs.correiostracker.viewmodel.*
import dagger.hilt.android.*

@AndroidEntryPoint
class InTransitFragment: Fragment() {
	
	private var _binding: FragmentInTransitBinding? = null
	private val binding get() = _binding!!
	
	val viewModel by viewModels<InTransitViewModel>()
	
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		_binding = FragmentInTransitBinding.inflate(inflater, container, false)
		
		return binding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		viewModel.parcels.observe(viewLifecycleOwner) {
		
		}
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		
		_binding = null
	}
}