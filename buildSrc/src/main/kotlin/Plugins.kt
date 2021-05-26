import org.gradle.plugin.use.*

inline val PluginDependenciesSpec.`android-application` get() = id("com.android.application")
inline val PluginDependenciesSpec.`kotlin-android` get() = id("kotlin-android")
inline val PluginDependenciesSpec.`kotlin-android-extensions` get() = id("kotlin-android-extensions")
