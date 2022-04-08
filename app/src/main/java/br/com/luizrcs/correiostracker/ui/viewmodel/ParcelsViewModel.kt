package br.com.luizrcs.correiostracker.ui.viewmodel

import android.util.*
import androidx.lifecycle.*
import br.com.luizrcs.correiostracker.repository.*
import br.com.luizrcs.correiostracker.ui.util.extensions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

abstract class ParcelsViewModel(val correiosRepository: CorreiosRepository, private val onlyFinished: Boolean): ViewModel() {
	
	lateinit var parcels: List<Parcel>
	val filteredParcels = MutableLiveData<List<Parcel>>()
	
	val changeFailed = MutableLiveData(false)
	
	private val finishedTypes = arrayOf("BDE", "BDI", "BDR")
	
	fun editParcel(name: String, trackingCode: String) = changeParcels { correiosRepository.editParcel(name, trackingCode) }
	fun removeParcel(trackingCode: String) = changeParcels { correiosRepository.removeParcel(trackingCode) }
	fun refreshParcels() = changeParcels { withContext(IO) { correiosRepository.refreshParcels() } }
	
	protected fun loadParcelsFromRepository() {
		parcels = correiosRepository.parcels
		filteredParcels.value = parcels
			.filter {
				val event = it.parcelEvents?.firstOrNull()
				event == null || onlyFinished == finishedTypes.contains(event.type)
			}
	}
	
	protected inline fun changeParcels(crossinline block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch {
		try {
			changeFailed.value = false
			block()
			loadParcelsFromRepository()
		} catch (e: Exception) {
			if (isDebug) Log.e(null, e.stackTraceToString())
			changeFailed.value = true
		}
	}
}