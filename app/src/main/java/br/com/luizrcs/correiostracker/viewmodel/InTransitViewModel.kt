package br.com.luizrcs.correiostracker.viewmodel

import androidx.lifecycle.*
import br.com.luizrcs.correiostracker.repository.*

class InTransitViewModel(savedStateHandle: SavedStateHandle): ViewModel() {
	
	val parcels: LiveData<List<Object>> = TODO()
}