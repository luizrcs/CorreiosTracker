package br.com.luizrcs.correiostracker.ui.fragment

import android.os.*
import android.view.*
import androidx.fragment.app.*
import androidx.recyclerview.widget.*
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
		_binding = FragmentParcelDetailsBinding.inflate(inflater, container, false)
		
		val index = requireArguments().getInt("index")
		val parcel = viewModel.parcels.value!![index]
		
		binding.recyclerViewParcelDetails.apply {
			adapter = ParcelDetailsAdapter(parcel)
			layoutManager = LinearLayoutManager(context).apply { orientation = RecyclerView.VERTICAL }
		}
		
		return binding.root
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		
		_binding = null
	}
}