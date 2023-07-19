@file:Suppress("UnstableApiUsage")

plugins {
	id("com.android.application") version Versions.androidApplication
	id("org.jetbrains.kotlin.android") version Versions.kotlin
	id("org.jetbrains.kotlin.kapt") version Versions.kotlin
	id("com.google.dagger.hilt.android") version Versions.hilt
	id("androidx.navigation.safeargs.kotlin") version Versions.navigationSafeArgs
}

val id = "br.com.luizrcs.correiostracker"

android {
	buildToolsVersion = "34.0.0"
	compileSdk = 34
	
	namespace = id
	
	defaultConfig {
		applicationId = id
		
		minSdk = 21
		targetSdk = 33
		
		versionCode = 1
		versionName = "1.0.0"
	}
	
	buildTypes {
		getByName("release") {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}
	
	buildFeatures {
		compose = true
		viewBinding = true
	}
	
	composeOptions {
		kotlinCompilerExtensionVersion = Versions.kotlinCompilerExtension
	}
	
	kotlinOptions {
		apiVersion = "1.9"
		languageVersion = "1.9"
		jvmTarget = "1.8"
		
		freeCompilerArgs += listOf(
			"-Xopt-in=kotlin.RequiresOptIn",
			"-P",
			"plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=true"
		)
	}
}

kapt {
	correctErrorTypes = true
}

repositories {
	maven("https://dl.bintray.com/kotlin/kotlinx")
}

dependencies {
	kotlin("stdlib", Versions.kotlin)
	
	implementation("com.google.dagger", "hilt-android", Versions.hilt)
	kapt("com.google.dagger", "hilt-android-compiler", Versions.hilt)
	
	androidx("appcompat", Versions.appCompat)
	androidx("cardview", Versions.cardView)
	androidxKtx("collection", Versions.collection)
	androidx("constraintlayout", Versions.constraintLayout)
	androidxKtx("core", Versions.coreKtx)
	androidxKtx("lifecycle", "livedata", Versions.lifecycleKtx)
	androidxKtx("lifecycle", "runtime", Versions.lifecycleKtx)
	androidxKtx("lifecycle", "viewmodel", Versions.lifecycleKtx)
	androidxKtx("navigation", "fragment", Versions.navigationKtx)
	androidxKtx("navigation", "runtime", Versions.navigationKtx)
	androidxKtx("navigation", "ui", Versions.navigationKtx)
	androidxKtx("palette", Versions.paletteKtx)
	androidx("recyclerview", Versions.recyclerView)
	androidx("swiperefreshlayout", Versions.swipeRefreshLayout)
	
	compose("animation")
	compose("material", name = "icons-extended")
	compose("material3", version = Versions.material3Compose)
	compose("runtime", name = "livedata")
	compose("ui", name = "tooling")
	androidx("activity", "compose", Versions.activityCompose)
	androidx("constraintlayout", "compose", Versions.constraintLayoutCompose)
	androidx("lifecycle", "viewmodel-compose", Versions.lifecycleViewModelCompose)
	androidx("navigation", "compose", Versions.navigationKtx)
	accompanist("swiperefresh")
	
	implementation("com.google.android.material", "material", Versions.material)
	
	coil()
	coil("compose")
	
	implementation("com.github.salomonbrys.kotson", "kotson", Versions.kotson)
	implementation("com.google.code.gson", "gson", Versions.gson)
	
	implementation("joda-time", "joda-time", Versions.jodaTime)
	
	okHttp("logging-interceptor", Versions.okHttp)
	retrofit("converter-gson", Versions.retrofit)
	retrofit(Versions.retrofit)
}
