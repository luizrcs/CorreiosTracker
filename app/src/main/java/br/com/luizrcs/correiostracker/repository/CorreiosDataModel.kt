package br.com.luizrcs.correiostracker.repository

import com.google.gson.annotations.SerializedName as Json

data class TrackingResponse(
	@Json("versao") val version: Double,
	@Json("quantidade") val quantity: Int,
	@Json("pesquisa") val queryType: String,
	@Json("resultado") val resultType: String,
	@Json("objeto") val objects: List<Object>
)

data class Object(
	@Json("numero") val trackingNumber: String,
	@Json("sigla") val serviceCode: String,
	@Json("nome") val serviceName: String,
	@Json("categoria") val serviceType: String,
	@Json("evento") val events: List<Evento>
) {
	
	val countryCode get() = trackingNumber.takeLast(2)
}

data class Evento(
	@Json("tipo") val type: String,
	@Json("status") val status: Int,
	@Json("criacao") val createdAt: Long,
	@Json("data") val date: String,
	@Json("hora") val time: String,
	@Json("descricao") val description: String,
	@Json("detalhe") val details: String,
	@Json("recebedor") val receiver: Any,
	@Json("cepDestino") val cep: Int,
	@Json("unidade") val postOffice: PostOffice,
	@Json("dataPostagem") val postingDate: String,
	@Json("diasUteis") val businessDays: Int,
	@Json("prazoGuarda") val storageDays: Int
)

data class PostOffice(
	@Json("codigo") val code: Int,
	@Json("sto") val sto: Int,
	@Json("local") val name: String,
	@Json("tipounidade") val type: String,
	@Json("endereco") val address: Address,
	@Json("cidade") val city: String,
	@Json("uf") val state: String
)

data class Address(
	@Json("codigo") val code: Int,
	@Json("cep") val cep: Int,
	@Json("logradouro") val street: String,
	@Json("numero") val number: Int,
	@Json("bairro") val neighbourhood: String,
	@Json("localidade") val city: String,
	@Json("uf") val uf: String,
	@Json("latitude") val latitude: Double,
	@Json("longitude") val longitude: Double
)