package br.com.luizrcs.correiostracker.ui.viewmodel

import androidx.lifecycle.*
import br.com.luizrcs.correiostracker.repository.*
import dagger.hilt.android.lifecycle.*
import kotlinx.coroutines.*
import javax.inject.*

@HiltViewModel
class InTransitViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	private val correiosRepository: CorreiosRepository
): ViewModel() {
	
	val failure = MutableLiveData(false)
	val parcels = MutableLiveData<List<Parcel>>()
	
	init {
		loadParcelsFromRepository()
		refreshParcels()
	}
	
	fun addParcel(name: String, trackingCode: String) =
		changeParcels { correiosRepository.addParcel(name, trackingCode) }
	
	fun editParcel(name: String, trackingCode: String) {
		correiosRepository.editParcel(name, trackingCode)
		loadParcelsFromRepository()
	}
	
	fun refreshParcels() = changeParcels { correiosRepository.refreshParcels() }
	
	private fun loadParcelsFromRepository() {
		parcels.value = correiosRepository.parcels
	}
	
	private inline fun changeParcels(crossinline block: suspend () -> Unit) {
		viewModelScope.launch {
			try {
				failure.value = false
				block()
				loadParcelsFromRepository()
			} catch (e: Exception) {
				failure.value = true
			}
		}
	}
}