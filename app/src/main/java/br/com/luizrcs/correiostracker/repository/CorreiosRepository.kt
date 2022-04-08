package br.com.luizrcs.correiostracker.repository

import br.com.luizrcs.correiostracker.ui.util.*
import com.github.salomonbrys.kotson.*
import com.google.gson.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.*
import javax.inject.*
import kotlin.math.*

class CorreiosRepository @Inject constructor(
	filesDir: File,
	private val gson: Gson,
	private val webService: CorreiosWebService,
) {
	
	private val parcelsFile = File(filesDir, "parcels.json")
	
	private var _parcels = loadParcels()
	val parcels get() = _parcels.sortedByDescending { it.parcelEvents?.first()?.createdAt?.toDate() }
	
	private val xmlMediaType = "application/xml".toMediaType()
	
	private var lastRefresh = 0L
	
	suspend fun addParcel(name: String, trackingCode: String) {
		_parcels += Parcel(name, trackingCode)
		refreshParcels()
	}
	
	fun removeParcel(trackingCode: String) {
		_parcels.removeAll { it.trackingCode == trackingCode }
	}
	
	fun editParcel(name: String, trackingCode: String) {
		val index = _parcels.indexOfFirst { it.trackingCode == trackingCode }
		val parcel = _parcels[index]
		_parcels[index] = parcel.copy(name = name)
	}
	
	suspend fun refreshParcels() {
		val newParcels = _refreshParcels() ?: return
		
		_parcels = _parcels
			.map { oldParcel ->
				val newParcel = newParcels.getValue(oldParcel.trackingCode)
				if (newParcel.parcelEvents == null) oldParcel
				else {
					if (oldParcel.parcelEvents == null) newParcel.copy(name = oldParcel.name)
					else {
						val oldParcelEvents = oldParcel.parcelEvents!!
						val newParcelEvents = newParcel.parcelEvents!!
						val deltaParcelEvents = max(newParcelEvents.size - oldParcelEvents.size, 0)
						oldParcel.apply { parcelEvents = newParcelEvents.take(deltaParcelEvents) + oldParcelEvents }
					}
				}
			}
			.toMutableList()
		
		saveParcels()
	}
	
	private suspend fun _refreshParcels(): Map<String, Parcel>? {
		val now = System.currentTimeMillis()
		
		return if (now - lastRefresh < 5000L) null
		else {
			val trackingCodes = _parcels.map { it.trackingCode }
			val requestBody = buildXml(trackingCodes).toRequestBody(xmlMediaType)
			val newParcels = webService.track(requestBody).parcels
			
			lastRefresh = now
			
			newParcels.associateBy { it.trackingCode }
		}
	}
	
	private fun loadParcels() =
		if (parcelsFile.exists()) FileReader(parcelsFile).use { gson.fromJson<MutableList<Parcel>>(it) }
		else mutableListOf()
	
	private fun saveParcels() = FileWriter(parcelsFile).use { gson.toJson(_parcels, it) }
}