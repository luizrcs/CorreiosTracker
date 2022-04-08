package br.com.luizrcs.correiostracker.ui.viewmodel

import androidx.lifecycle.*
import br.com.luizrcs.correiostracker.repository.*
import dagger.hilt.android.lifecycle.*
import javax.inject.*

@HiltViewModel
class FinishedViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	correiosRepository: CorreiosRepository,
): ParcelsViewModel(correiosRepository, true) {
	
	init {
		loadParcelsFromRepository()
		refreshParcels()
	}
}