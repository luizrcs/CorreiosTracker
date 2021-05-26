package br.com.luizrcs.correiostracker.hilt

import br.com.luizrcs.correiostracker.repository.*
import dagger.*
import dagger.hilt.*
import dagger.hilt.components.*
import okhttp3.*
import okhttp3.logging.*
import retrofit2.*
import retrofit2.converter.gson.*
import javax.inject.*

@Module
@InstallIn(SingletonComponent::class)
class CorreiosTrackerModule {
	
	@Provides
	@Singleton
	fun provideOkHttpClient() =
		OkHttpClient.Builder()
			.build()
	
	@Provides
	@Singleton
	fun provideRetrofit(okHttpClient: OkHttpClient) =
		Retrofit.Builder()
			.client(okHttpClient)
			.baseUrl(baseUrl)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
	
	@Provides
	@Singleton
	fun provideCorreiosWebService(retrofit: Retrofit) = retrofit.create(CorreiosWebService::class.java)
	
	@Provides
	@Singleton
	fun provideCorreiosRepository(correiosWebService: CorreiosWebService) = CorreiosRepository(correiosWebService)
	
	companion object {
		
		const val baseUrl = "https://webservice.correios.com.br/service/rest/"
	}
}