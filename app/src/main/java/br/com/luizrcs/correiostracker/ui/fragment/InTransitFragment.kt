package br.com.luizrcs.correiostracker.ui.fragment

import android.os.*
import android.text.*
import android.util.*
import android.view.*
import android.widget.*
import androidx.core.content.res.ResourcesCompat.*
import androidx.core.widget.*
import androidx.fragment.app.*
import androidx.lifecycle.*
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.RecyclerView.*
import br.com.luizrcs.correiostracker.R
import br.com.luizrcs.correiostracker.databinding.*
import br.com.luizrcs.correiostracker.ui.activity.*
import br.com.luizrcs.correiostracker.ui.recyclerview.*
import br.com.luizrcs.correiostracker.ui.util.*
import br.com.luizrcs.correiostracker.ui.viewmodel.*
import com.google.android.material.dialog.*
import dagger.hilt.android.*
import java.util.concurrent.*

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
		
		with(activity as MainActivity) {
			binding.fab.run {
				showAnimated()
				
				setOnClickListener {
					val dialogBinding = DialogAddParcelBinding.inflate(layoutInflater)
					
					val dialog = MaterialAlertDialogBuilder(requireContext())
						.setView(dialogBinding.root)
						.setBackground(getDrawable(resources, R.drawable.dialog_shape, null))
						.show()
					
					dialogBinding.code.apply {
						filters = arrayOf(trackingCodeInputFilter)
						addTextChangedListener { dialogBinding.codeLayout.error = null }
					}
					
					dialogBinding.cancel.setOnClickListener { dialog.dismiss() }
					dialogBinding.confirm.setOnClickListener {
						if (dialogBinding.code.text?.isNotBlank() == true) {
							val code = dialogBinding.code.text.toString()
							val name = dialogBinding.name.text
								?.let { if (it.isNotBlank()) it.toString().trim() else code.formatTrackingCode() }
								?: code.formatTrackingCode()
							
							viewModel.addParcel(name, code).invokeOnCompletion {
								Toast.makeText(
									context, when (it) {
										null                     -> R.string.parcel_added
										is CancellationException -> R.string.parcel_already_exists
										else                     -> R.string.parcel_add_fail
									},
									Toast.LENGTH_SHORT
								).show()
							}
							
							dialog.dismiss()
						} else dialogBinding.codeLayout.error = getString(R.string.dialog_add_parcel_empty_tracking_code)
					}
				}
			}
		}
		
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
		
		with(activity as MainActivity) {
			binding.fab.setOnClickListener(null)
		}
		
		_binding = null
	}
}