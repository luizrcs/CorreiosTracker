import org.gradle.kotlin.dsl.*
import org.gradle.plugin.use.*

inline val PluginDependenciesSpec.`android-application` get() = id("com.android.application")
inline val PluginDependenciesSpec.`kotlin-android` get() = id("kotlin-android")
inline val PluginDependenciesSpec.kapt get() = id("org.jetbrains.kotlin.kapt")
