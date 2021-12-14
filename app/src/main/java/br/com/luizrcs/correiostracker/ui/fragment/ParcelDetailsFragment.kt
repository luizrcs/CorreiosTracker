package br.com.luizrcs.correiostracker.ui.fragment

import android.os.*
import android.view.*
import androidx.core.content.*
import androidx.core.content.res.*
import androidx.fragment.app.*
import androidx.navigation.fragment.*
import androidx.recyclerview.widget.*
import br.com.luizrcs.correiostracker.R
import br.com.luizrcs.correiostracker.databinding.*
import br.com.luizrcs.correiostracker.ui.activity.*
import br.com.luizrcs.correiostracker.ui.recyclerview.*
import br.com.luizrcs.correiostracker.ui.util.*
import br.com.luizrcs.correiostracker.ui.util.extensions.*
import br.com.luizrcs.correiostracker.ui.viewmodel.*
import com.google.android.material.appbar.AppBarLayout.*
import dagger.hilt.android.*
import kotlin.math.*

@AndroidEntryPoint
class ParcelDetailsFragment: Fragment() {
	
	private var _binding: FragmentParcelDetailsBinding? = null
	private val binding get() = _binding!!
	
	val viewModel by viewModels<ParcelDetailsViewModel>()
	
	private var hideDetailsToolbar = false
	
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		super.onCreateView(inflater, container, savedInstanceState)
		
		_binding = FragmentParcelDetailsBinding.inflate(inflater, container, false)
		
		return binding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		var color = 0
		
		with(activity as MainActivity) {
			binding.toolbar.hideAnimated { animateStatusBarColor(window.statusBarColor, color) }
			binding.fab.hideAnimated()
			binding.bottomNavigation.hideAnimated { animateNavigationBarColor(window.statusBarColor, color) }
		}
		
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
		
		binding.appBar.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset ->
			val percentage = abs(verticalOffset).toFloat() / appBarLayout.totalScrollRange.toFloat()
			if (percentage == 1f && hideDetailsToolbar) {
				hideDetailsToolbar = false
				binding.disclaimer.hide()
				binding.detailsBarContents.show()
			} else if (percentage < 1f && !hideDetailsToolbar) {
				hideDetailsToolbar = true
				binding.disclaimer.show()
				binding.detailsBarContents.hide()
			}
		})
		
		binding.toolbarLayout.title = parcel.name
		
		val days = parcel.daysElapsed() ?: 0
		
		binding.collapsingDate.text = resources.getQuantityString(R.plurals.parcel_collapsing_details_date, days, days)
		binding.collapsingCode.text = parcel.trackingCode
		binding.collapsingCategory.text = parcel.serviceType.split(" ")
			.let { split ->
				split.mapIndexed { index, s ->
					if (index >= split.lastIndex - 1 && s.length <= 8) s.uppercase()
					else s.capitalizeWord()
				}
			}
			.joinToString(" ")
		
		binding.date.text = resources.getQuantityString(R.plurals.parcel_details_date, days, days)
		binding.code.text = parcel.trackingCode
		binding.category.text = parcel.serviceType.substringAfterLast(' ')
			.let { if (it.length <= 8) it.uppercase() else it.capitalizeWord() }
		
		if (parcel.countryCode != "BR") {
			binding.collapsingCategoryIcon.setImageResource(R.drawable.outline_public_24)
			binding.categoryIcon.setImageResource(R.drawable.outline_public_24)
		}
		
		val event = parcel.parcelEvents!!.first()
		val statusStyle = event.statusStyle()
		color = ContextCompat.getColor(requireContext(), statusStyle.colorId)
		
		binding.appBar.setBackgroundColor(color)
		binding.toolbar.setBackgroundColor(color)
		binding.detailsBar.setBackgroundColorFilter(color)
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		
		_binding = null
	}
}

private fun View.hide() = animate()
	.setDuration(200)
	.alpha(0.0f)
	.withEndAction { visibility = View.INVISIBLE }

private fun View.show() = animate()
	.setDuration(200)
	.alpha(1.0f)
	.withStartAction { visibility = View.VISIBLE }