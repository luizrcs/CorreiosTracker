package br.com.luizrcs.correiostracker.ui.viewmodel

import br.com.luizrcs.correiostracker.repository.*
import dagger.hilt.android.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.*

@HiltViewModel
class MainViewModel @Inject constructor(
	correiosRepository: CorreiosRepository,
): ParcelsViewModel(correiosRepository, false) {
	
	init {
		loadParcelsFromRepository()
		refreshParcels()
	}
	
	fun addParcel(name: String, trackingCode: String) = changeParcels {
		if (correiosRepository.parcels.any { it.trackingCode == trackingCode }) cancel()
		else withContext(IO) { correiosRepository.addParcel(name, trackingCode) }
	}
}