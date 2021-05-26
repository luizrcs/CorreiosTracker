package br.com.luizrcs.correiostracker.fragment

import android.os.*
import android.view.*
import androidx.fragment.app.*
import androidx.lifecycle.*
import br.com.luizrcs.correiostracker.*
import br.com.luizrcs.correiostracker.viewmodel.*

class InTransitFragment: Fragment() {
	
	val viewModel by viewModels<InTransitViewModel>(
		{ this },
		{ SavedStateViewModelFactory(activity?.application, this) }
	)
	
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
		inflater.inflate(R.layout.fragment_in_transit, container, false)
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		viewModel.parcels.observe(viewLifecycleOwner) {
		
		}
	}
}