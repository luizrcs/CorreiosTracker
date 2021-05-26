package br.com.luizrcs.correiostracker.viewmodel

import android.util.*
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
				_parcels.value = withContext(IO) { correiosRepository.getParcels(emptyList()).objects }
			} catch (e: Exception) {
			}
		}
	}
}