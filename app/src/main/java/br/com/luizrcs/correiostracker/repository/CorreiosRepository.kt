package br.com.luizrcs.correiostracker.repository

import br.com.luizrcs.correiostracker.ui.util.*
import com.github.salomonbrys.kotson.*
import com.google.gson.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.*
import javax.inject.*

class CorreiosRepository @Inject constructor(
	filesDir: File,
	private val gson: Gson,
	private val webService: CorreiosWebService
) {
	
	private val parcelsFile = File(filesDir, "parcels.json")
	
	private var _parcels = loadParcels()
	val parcels get() = _parcels.sortedByDescending { it.parcelEvents?.first()?.createdAt?.toDate() }
	
	private val xmlMediaType = "application/xml".toMediaType()
	
	suspend fun addParcel(name: String, trackingCode: String) {
		_parcels += Parcel(name, trackingCode)
		refreshParcels()
	}
	
	fun editParcel(name: String, trackingCode: String) {
		val index = _parcels.indexOfFirst { it.trackingCode == trackingCode }
		val parcel = _parcels[index]
		_parcels[index] = parcel.copy(name = name)
	}
	
	private suspend fun _refreshParcels(): Map<String, Parcel> {
		val trackingCodes = _parcels.map { it.trackingCode }
		val requestBody = buildXml(trackingCodes).toRequestBody(xmlMediaType)
		return webService.track(requestBody).parcels.associateBy { it.trackingCode }
	}
	
	suspend fun refreshParcels() {
		val newParcels = withContext(IO) { _refreshParcels() }
		
		_parcels = _parcels
			.map { oldParcel ->
				val newParcel = newParcels.getValue(oldParcel.trackingCode)
				if (newParcel.parcelEvents == null) oldParcel
				else {
					if (oldParcel.parcelEvents == null) newParcel.copy(name = oldParcel.name)
					else {
						val oldParcelEvents = oldParcel.parcelEvents!!
						val newParcelEvents = newParcel.parcelEvents!!
						val deltaParcelEvents = newParcelEvents.size - oldParcelEvents.size
						oldParcel.apply { parcelEvents = newParcelEvents.take(deltaParcelEvents) + oldParcelEvents }
					}
				}
			}
			.toMutableList()
		
		withContext(IO) { saveParcels() }
	}
	
	private fun loadParcels() =
		if (parcelsFile.exists()) FileReader(parcelsFile).use { gson.fromJson<MutableList<Parcel>>(it) }
		else mutableListOf()
	
	private fun saveParcels() = FileWriter(parcelsFile).use { gson.toJson(_parcels, it) }
}