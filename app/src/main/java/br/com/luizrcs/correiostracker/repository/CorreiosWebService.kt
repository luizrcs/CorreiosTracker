package br.com.luizrcs.correiostracker.repository

import okhttp3.*
import retrofit2.http.*
import retrofit2.http.Headers

interface CorreiosWebService {
	
	@Headers("Content-Type: application/xml")
	@POST("rastro/rastroMobile")
	suspend fun track(@Body requestBody: RequestBody): TrackingResponse
}

fun buildXml(root: String, vararg children: Pair<String, String>) = buildString {
	append('<').append(root).append('>')
	
	children.forEach { (tag, value) ->
		append('<').append(tag).append('>')
		append(value)
		append("</").append(tag).append('>')
	}
	
	append("</").append(root).append('>')
}

fun buildXml(trackingCodes: List<String>) = buildXml(
	"rastroObjeto",
	"tipo" to "L",
	"resultado" to "T",
	"objetos" to joinTrackingCodes(trackingCodes),
	"lingua" to "101"
)

fun joinTrackingCodes(trackingCodes: List<String>) = buildString { trackingCodes.forEach(::append) }