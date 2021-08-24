package br.com.luizrcs.correiostracker.ui.viewmodel

import androidx.lifecycle.*
import br.com.luizrcs.correiostracker.repository.*
import dagger.hilt.android.lifecycle.*
import kotlinx.coroutines.*
import javax.inject.*

@HiltViewModel
class FinishedViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	private val correiosRepository: CorreiosRepository
): ParcelsViewModel() {
	
	init {
		loadParcelsFromRepository()
		refreshParcels()
	}
	
	override fun editParcel(name: String, trackingCode: String) {
		correiosRepository.editParcel(name, trackingCode)
		loadParcelsFromRepository()
	}
	
	override fun refreshParcels() = changeParcels { correiosRepository.refreshParcels() }
	
	override fun loadParcelsFromRepository() {
		parcels.value = correiosRepository.parcels
			.filter {
				val event = it.parcelEvents?.firstOrNull()
				event != null && event.type in finishedTypes
			}
	}
}