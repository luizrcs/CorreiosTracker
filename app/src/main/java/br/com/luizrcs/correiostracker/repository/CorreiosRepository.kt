package br.com.luizrcs.correiostracker.repository

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.*

class CorreiosRepository @Inject constructor(private val webService: CorreiosWebService) {
	
	suspend fun getParcels(trackingCodes: List<String>) =
		webService.track(buildXml(trackingCodes).toRequestBody("application/xml".toMediaType()))
}