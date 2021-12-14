package br.com.luizrcs.correiostracker.ui.viewmodel

import androidx.lifecycle.*
import br.com.luizrcs.correiostracker.repository.*
import dagger.hilt.android.lifecycle.*
import javax.inject.*

@HiltViewModel
class InTransitViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	private val correiosRepository: CorreiosRepository,
): ParcelsViewModel() {
	
	init {
		loadParcelsFromRepository()
		refreshParcels()
	}
	
	fun addParcel(name: String, trackingCode: String) =
		changeParcels { correiosRepository.addParcel(name, trackingCode) }
	
	override fun editParcel(name: String, trackingCode: String) {
		correiosRepository.editParcel(name, trackingCode)
		loadParcelsFromRepository()
	}
	
	override fun refreshParcels() = changeParcels { correiosRepository.refreshParcels() }
	
	override fun loadParcelsFromRepository() {
		parcels = correiosRepository.parcels
		filteredParcels.value = parcels
			.filter {
				val event = it.parcelEvents?.firstOrNull()
				event == null || event.type !in finishedTypes
			}
	}
}