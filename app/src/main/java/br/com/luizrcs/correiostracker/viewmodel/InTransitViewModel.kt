package br.com.luizrcs.correiostracker.viewmodel

import androidx.lifecycle.*
import br.com.luizrcs.correiostracker.repository.*
import dagger.hilt.android.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.*

@HiltViewModel
class InTransitViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	correiosRepository: CorreiosRepository
): ViewModel() {
	
	private val _parcels: MutableLiveData<List<Object>> = MutableLiveData()
	val parcels: LiveData<List<Object>> by ::_parcels
	
	init {
		viewModelScope.launch {
			try {
				val trackingCodes = listOf(
					"OF405104119BR",
					"LE261562552SE",
					"OF405104119BR",
					"LE261562552SE",
					"OF405104119BR",
					"LE261562552SE"
				)
				withContext(IO) { correiosRepository.refreshParcels(trackingCodes) }
				_parcels.value = correiosRepository.parcels
			} catch (e: Exception) {
			}
		}
	}
}