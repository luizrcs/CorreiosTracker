package br.com.luizrcs.correiostracker.ui.viewmodel

import androidx.lifecycle.*
import br.com.luizrcs.correiostracker.repository.*
import kotlinx.coroutines.*

abstract class ParcelsViewModel: ViewModel() {
	
	lateinit var parcels: List<Parcel>
	val filteredParcels = MutableLiveData<List<Parcel>>()
	
	val changeFailed = MutableLiveData(false)
	
	protected val finishedTypes = arrayOf("BDE", "BDI", "BDR")
	
	abstract fun editParcel(name: String, trackingCode: String)
	abstract fun refreshParcels()
	
	protected abstract fun loadParcelsFromRepository()
	
	protected inline fun changeParcels(crossinline block: suspend () -> Unit) {
		viewModelScope.launch {
			try {
				changeFailed.value = false
				block()
				loadParcelsFromRepository()
			} catch (e: Exception) {
				changeFailed.value = true
			}
		}
	}
}