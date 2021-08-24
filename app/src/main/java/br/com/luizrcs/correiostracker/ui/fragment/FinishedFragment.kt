package br.com.luizrcs.correiostracker.ui.fragment

import android.os.*
import android.view.*
import android.widget.*
import androidx.core.content.res.*
import androidx.core.widget.*
import androidx.fragment.app.*
import androidx.lifecycle.*
import androidx.recyclerview.widget.*
import br.com.luizrcs.correiostracker.*
import br.com.luizrcs.correiostracker.R
import br.com.luizrcs.correiostracker.databinding.*
import br.com.luizrcs.correiostracker.ui.recyclerview.*
import br.com.luizrcs.correiostracker.ui.util.*
import br.com.luizrcs.correiostracker.ui.viewmodel.*
import com.google.android.material.dialog.*
import dagger.hilt.android.*

@AndroidEntryPoint
class FinishedFragment: CustomFragment() {
	
	private var _binding: FragmentFinishedBinding? = null
	private val binding get() = _binding!!
	
	val viewModel by viewModels<FinishedViewModel>()
	
	private var shouldAnnounce = false
	
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		_binding = FragmentFinishedBinding.inflate(inflater, container, false)
		
		return binding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		binding.swipeRefresh.setOnRefreshListener {
			shouldAnnounce = true
			viewModel.refreshParcels()
		}
		
		binding.recyclerView.apply {
			adapter = FinishedAdapter(viewModel)
			layoutManager = LinearLayoutManager(context).apply { orientation = RecyclerView.VERTICAL }
		}
		
		viewModel.failure.observe(viewLifecycleOwner) {
			binding.swipeRefresh.isRefreshing = false
			
			if (it) Toast.makeText(context, R.string.parcels_failure, Toast.LENGTH_SHORT).show()
		}
		
		viewModel.parcels.observe(viewLifecycleOwner) {
			binding.swipeRefresh.visibility = if (it.isEmpty()) RecyclerView.GONE else RecyclerView.VISIBLE
			binding.noParcels.visibility = if (it.isEmpty()) RecyclerView.VISIBLE else RecyclerView.GONE
			binding.recyclerView.adapter?.notifyDataSetChanged()
			
			if (shouldAnnounce) {
				shouldAnnounce = false
				
				Toast.makeText(context, R.string.parcels_updated, Toast.LENGTH_SHORT).show()
			}
		}
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		
		_binding = null
	}
}