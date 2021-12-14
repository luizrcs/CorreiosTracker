package br.com.luizrcs.correiostracker.ui.viewmodel

import androidx.lifecycle.*
import br.com.luizrcs.correiostracker.repository.*
import dagger.hilt.android.lifecycle.*
import javax.inject.*

@HiltViewModel
class ParcelDetailsViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	private val correiosRepository: CorreiosRepository,
): ViewModel() {
	
	val parcels = MutableLiveData<List<Parcel>>()
	
	init {
		loadParcelsFromRepository()
	}
	
	private fun loadParcelsFromRepository() {
		parcels.value = correiosRepository.parcels
	}
}