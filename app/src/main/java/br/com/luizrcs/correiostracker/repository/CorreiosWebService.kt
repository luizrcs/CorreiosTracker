package br.com.luizrcs.correiostracker.repository

import retrofit2.http.*

interface CorreiosWebService {
	
	@Headers("Content-Type: application/xml")
	@POST("/rastro/rastroMobile")
	suspend fun track(@Body xml: String): TrackingResponse
	
	companion object {
		
		const val baseUrl = "https://webservice.correios.com.br/service/rest"
	}
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

fun buildXml(username: String, password: String, token: String, objects: List<String>) = buildXml(
	"rastroObjeto",
	"usuario" to username,
	"senha" to password,
	"token" to token,
	"tipo" to "L",
	"resultado" to "T",
	"objetos" to joinObjects(objects),
	"lingua" to "101"
)

fun joinObjects(objects: List<String>) = buildString { objects.forEach(::append) }