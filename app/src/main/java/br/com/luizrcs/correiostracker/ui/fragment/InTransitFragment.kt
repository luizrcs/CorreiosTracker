package br.com.luizrcs.correiostracker.ui.fragment

import android.os.*
import android.text.*
import android.view.*
import android.widget.*
import androidx.fragment.app.*
import androidx.lifecycle.*
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.RecyclerView.*
import br.com.luizrcs.correiostracker.R
import br.com.luizrcs.correiostracker.databinding.*
import br.com.luizrcs.correiostracker.ui.recyclerview.*
import br.com.luizrcs.correiostracker.ui.viewmodel.*
import dagger.hilt.android.*

@AndroidEntryPoint
class InTransitFragment: AppScreenFragment() {
	
	private var _binding: FragmentInTransitBinding? = null
	private val binding get() = _binding!!
	
	val viewModel by viewModels<InTransitViewModel>()
	
	private var shouldAnnounce = false
	
	private val trackingCodeInputFilter = InputFilter { source, start, end, dest, dStart, dEnd ->
		when {
			dStart > 12 -> ""
			end != 0    -> source
				.asSequence()
				.filterIndexed { index, char ->
					if (dStart + index in 2 .. 10) char.isDigit() else char.isLetter()
				}
				.joinToString("") { it.uppercase() }
			else        -> source
		}
	}
	
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		_binding = FragmentInTransitBinding.inflate(inflater, container, false)
		
		return binding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		binding.parcelListing.swipeRefresh.apply {
			setOnRefreshListener {
				shouldAnnounce = true
				viewModel.refreshParcels().invokeOnCompletion { isRefreshing = false }
			}
		}
		
		binding.parcelListing.recyclerView.apply {
			adapter = InTransitAdapter(viewModel)
			layoutManager = LinearLayoutManager(context).apply { orientation = VERTICAL }
		}
		
		viewModel.changeFailed.observe(viewLifecycleOwner) {
			binding.parcelListing.swipeRefresh.isRefreshing = false
			
			if (it) Toast.makeText(context, R.string.parcels_failure, Toast.LENGTH_SHORT).show()
		}
		
		viewModel.filteredParcels.observe(viewLifecycleOwner) {
			val isEmpty = it.isEmpty()
			binding.parcelListing.swipeRefresh.visibility = if (isEmpty) GONE else VISIBLE
			binding.noParcelsIcon.visibility = if (isEmpty) VISIBLE else GONE
			binding.noParcelsText.visibility = if (isEmpty) VISIBLE else GONE
			
			binding.parcelListing.recyclerView.adapter?.notifyDataSetChanged()
			
			if (shouldAnnounce) {
				shouldAnnounce = false
				
				/*Snackbar.make(binding.fab, R.string.parcels_updated, Snackbar.LENGTH_SHORT)
					.apply { anchorView = (activity as MainActivity).binding.bottomNavigation }
					.show()*/
				
				Toast.makeText(context, R.string.parcels_updated, Toast.LENGTH_SHORT).show()
			}
		}
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		
		_binding = null
	}
}