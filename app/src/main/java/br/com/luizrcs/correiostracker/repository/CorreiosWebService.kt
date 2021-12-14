package br.com.luizrcs.correiostracker.repository

import okhttp3.*
import retrofit2.http.*

interface CorreiosWebService {
	
	@POST("rastro/rastroMobile")
	suspend fun track(@Body requestBody: RequestBody): TrackingResponse
}

fun buildXml(trackingCodes: List<String>) = """
	<rastroObjeto>
		<tipo>L</tipo>
		<resultado>T</resultado>
		<objetos>${joinTrackingCodes(trackingCodes)}</objetos>
		<lingua>101</lingua>
	</rastroObjeto>
""".trimIndent()

fun joinTrackingCodes(trackingCodes: List<String>) = buildString { trackingCodes.onEach(::append) }