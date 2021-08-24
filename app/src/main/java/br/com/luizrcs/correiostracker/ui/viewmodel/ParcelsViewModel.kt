package br.com.luizrcs.correiostracker.ui.viewmodel

import androidx.lifecycle.*
import br.com.luizrcs.correiostracker.repository.*
import dagger.hilt.android.lifecycle.*
import kotlinx.coroutines.*
import javax.inject.*

abstract class ParcelsViewModel: ViewModel() {
	
	val failure = MutableLiveData(false)
	val parcels = MutableLiveData<List<Parcel>>()
	
	protected val finishedTypes = arrayOf("BDE", "BDI", "BDR")
	
	abstract fun editParcel(name: String, trackingCode: String)
	abstract fun refreshParcels()
	
	protected abstract fun loadParcelsFromRepository()
	
	protected inline fun changeParcels(crossinline block: suspend () -> Unit) {
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