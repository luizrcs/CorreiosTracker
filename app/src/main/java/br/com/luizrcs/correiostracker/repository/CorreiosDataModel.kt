package br.com.luizrcs.correiostracker.repository

import com.google.gson.annotations.*

data class TrackingResponse(
	@SerializedName("objeto") val parcels: List<Parcel>
)

data class Parcel(
	val name: String,
	@SerializedName("numero") val trackingCode: String,
	@SerializedName("nome") val serviceName: String? = null,
	@SerializedName("categoria") val serviceType: String = "",
	@SerializedName("evento") var parcelEvents: List<ParcelEvent>? = null
) {
	
	val countryCode get() = trackingCode.takeLast(2)
}

data class ParcelEvent(
	@SerializedName("tipo") val type: String,
	@SerializedName("status") val status: Int,
	@SerializedName("criacao") val createdAt: String,
	@SerializedName("data") val date: String,
	@SerializedName("hora") val time: String,
	@SerializedName("descricao") val description: String,
	@SerializedName("detalhe") val details: String?,
	@SerializedName("cepDestino") val cep: Int,
	@SerializedName("unidade") val fromOffice: PostOffice,
	@SerializedName("destino") val toOffice: List<PostOffice>?
)

data class PostOffice(
	@SerializedName("codigo") val code: Int,
	@SerializedName("local") val name: String,
	@SerializedName("tipounidade") val type: String,
	@SerializedName("endereco") val address: Address
)

data class Address(
	@SerializedName("codigo") val code: Int,
	@SerializedName("cep") val cep: Int,
	@SerializedName("logradouro") val street: String,
	@SerializedName("numero") val number: Int,
	@SerializedName("bairro") val neighbourhood: String,
	@SerializedName("localidade") val city: String,
	@SerializedName("uf") val state: String,
	@SerializedName("latitude") val latitude: Double,
	@SerializedName("longitude") val longitude: Double
)