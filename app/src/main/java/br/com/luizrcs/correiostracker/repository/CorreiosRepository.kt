package br.com.luizrcs.correiostracker.repository

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.*

class CorreiosRepository @Inject constructor(private val webService: CorreiosWebService) {
	
	private val xmlMediaType = "application/xml".toMediaType()
	
	suspend fun getParcels(trackingCodes: List<String>) =
		webService.track(buildXml(trackingCodes).toRequestBody(xmlMediaType))
}