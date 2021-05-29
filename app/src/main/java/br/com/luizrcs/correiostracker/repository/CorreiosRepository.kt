package br.com.luizrcs.correiostracker.repository

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.*

class CorreiosRepository @Inject constructor(private val webService: CorreiosWebService) {
	
	private val xmlMediaType = "application/xml".toMediaType()
	
	private var _parcels = emptyList<Object>()
	val parcels by ::_parcels
	
	suspend fun refreshParcels(trackingCodes: List<String>) {
		_parcels = webService.track(buildXml(trackingCodes).toRequestBody(xmlMediaType)).objects
	}
}