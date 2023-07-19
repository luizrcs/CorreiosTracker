package br.com.luizrcs.correiostracker

import android.app.*
import android.content.*
import android.content.Context.*
import br.com.luizrcs.correiostracker.Preferences.Type.*
import dagger.hilt.android.*
import kotlin.reflect.*

@HiltAndroidApp
class CorreiosTrackerApplication: Application() {
	override fun onCreate() {
		super.onCreate()
		instance = this
	}
	
	companion object {
		lateinit var instance: CorreiosTrackerApplication
			private set
	}
}

object Preferences {
	private val application: Application = CorreiosTrackerApplication.instance
	private val sharedPreferences: SharedPreferences = application.getSharedPreferences("preferences", MODE_PRIVATE)
	
	var version by preference(1)
	
	private inline fun <reified T> preference(default: T? = null) = Preference(default, Type.get<T>())
	
	@Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
	private fun <T> SharedPreferences.get(key: String, type: Type) = if (contains(key)) when (type) {
		STRING     -> getString(key, null)
		STRING_SET -> getStringSet(key, null)
		INT        -> getInt(key, 0)
		LONG       -> getLong(key, 0L)
		FLOAT      -> getFloat(key, 0f)
		BOOLEAN    -> getBoolean(key, false)
	} as T? else null
	
	@Suppress("UNCHECKED_CAST")
	private fun <T> SharedPreferences.set(key: String, value: T, type: Type) = when (type) {
		STRING     -> edit().putString(key, value as String).apply()
		STRING_SET -> edit().putStringSet(key, value as Set<String>).apply()
		INT        -> edit().putInt(key, value as Int).apply()
		LONG       -> edit().putLong(key, value as Long).apply()
		FLOAT      -> edit().putFloat(key, value as Float).apply()
		BOOLEAN    -> edit().putBoolean(key, value as Boolean).apply()
	}
	
	class Preference<T>(private val default: T?, private val type: Type) {
		private var value: T? = null
		private var initialized = false
		
		operator fun getValue(preferences: Preferences, property: KProperty<*>): T? {
			if (!initialized) {
				value = sharedPreferences.get<T>(property.name, type) ?: default
				initialized = true
			}
			
			return value
		}
		
		operator fun setValue(preferences: Preferences, property: KProperty<*>, value: T?) {
			this.value = value
			sharedPreferences.set(property.name, value, type)
		}
	}
	
	enum class Type {
		STRING, STRING_SET, INT, LONG, FLOAT, BOOLEAN;
		
		companion object {
			inline fun <reified T> get() = when (T::class) {
				String::class  -> STRING
				Set::class     -> STRING_SET
				Int::class     -> INT
				Long::class    -> LONG
				Float::class   -> FLOAT
				Boolean::class -> BOOLEAN
				else           -> throw IllegalArgumentException("Unsupported type: ${T::class}")
			}
		}
	}
}