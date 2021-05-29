package br.com.luizrcs.correiostracker.fragment

import android.os.*
import android.view.*
import androidx.fragment.app.*
import androidx.lifecycle.*
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.RecyclerView.*
import br.com.luizrcs.correiostracker.databinding.*
import br.com.luizrcs.correiostracker.repository.*
import br.com.luizrcs.correiostracker.ui.*
import br.com.luizrcs.correiostracker.viewmodel.*
import dagger.hilt.android.*
import javax.inject.*

@AndroidEntryPoint
class InTransitFragment: Fragment() {
	
	@Inject lateinit var _correiosRepository: CorreiosRepository
	private val correiosRepository by ::_correiosRepository
	
	private var _binding: FragmentInTransitBinding? = null
	private val binding get() = _binding!!
	
	val viewModel by viewModels<InTransitViewModel>()
	
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		_binding = FragmentInTransitBinding.inflate(inflater, container, false)
		
		return binding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		binding.recyclerView.adapter = InTransitAdapter(correiosRepository)
		binding.recyclerView.layoutManager = LinearLayoutManager(context).apply { orientation = VERTICAL }
		
		viewModel.parcels.observe(viewLifecycleOwner) {
			binding.recyclerView.adapter?.notifyDataSetChanged()
		}
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		
		_binding = null
	}
}