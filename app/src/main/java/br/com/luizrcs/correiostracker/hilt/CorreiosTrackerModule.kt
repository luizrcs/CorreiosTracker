package br.com.luizrcs.correiostracker.hilt

import android.content.*
import br.com.luizrcs.correiostracker.repository.*
import com.google.gson.*
import dagger.*
import dagger.hilt.*
import dagger.hilt.android.qualifiers.*
import dagger.hilt.components.*
import okhttp3.*
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
	fun provideGson() = GsonBuilder().create()
	
	@Provides
	@Singleton
	fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson) =
		Retrofit.Builder()
			.client(okHttpClient)
			.baseUrl(baseUrl)
			.addConverterFactory(GsonConverterFactory.create(gson))
			.build()
	
	@Provides
	@Singleton
	fun provideCorreiosWebService(retrofit: Retrofit) = retrofit.create(CorreiosWebService::class.java)
	
	@Provides
	@Singleton
	fun provideCorreiosRepository(
		@ApplicationContext context: Context,
		gson: Gson,
		correiosWebService: CorreiosWebService
	) = CorreiosRepository(context.filesDir, gson, correiosWebService)
	
	companion object {
		
		const val baseUrl = "https://webservice.correios.com.br/service/rest/"
	}
}