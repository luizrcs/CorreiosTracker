package br.com.luizrcs.correiostracker.ui.fragment

import android.os.*
import android.view.*
import androidx.appcompat.widget.Toolbar.*
import androidx.appcompat.widget.Toolbar.LayoutParams.*
import androidx.core.content.res.*
import androidx.core.view.*
import androidx.fragment.app.*
import androidx.navigation.fragment.*
import androidx.recyclerview.widget.*
import br.com.luizrcs.correiostracker.R
import br.com.luizrcs.correiostracker.databinding.*
import br.com.luizrcs.correiostracker.ui.recyclerview.*
import br.com.luizrcs.correiostracker.ui.viewmodel.*
import dagger.hilt.android.*

@AndroidEntryPoint
class ParcelDetailsFragment: Fragment() {
	
	private var _binding: FragmentParcelDetailsBinding? = null
	private val binding get() = _binding!!
	
	val viewModel by viewModels<ParcelDetailsViewModel>()
	
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		super.onCreateView(inflater, container, savedInstanceState)
		
		_binding = FragmentParcelDetailsBinding.inflate(inflater, container, false)
		
		return binding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		val index = requireArguments().getInt("index")
		val parcel = viewModel.parcels.value!![index]
		
		binding.toolbar.apply {
			navigationIcon = ResourcesCompat.getDrawable(resources, R.drawable.outline_arrow_back_24, null)
			setNavigationOnClickListener { findNavController().navigateUp() }
		}
		
		binding.recyclerView.apply {
			adapter = ParcelDetailsAdapter(parcel)
			layoutManager = LinearLayoutManager(context).apply { orientation = RecyclerView.VERTICAL }
		}
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		
		_binding = null
	}
}