import org.gradle.api.JavaVersion.*

plugins {
	`android-application`
	`kotlin-android`
	kapt
	
	// id("androidx.navigation.safeargs.kotlin")
	id("dagger.hilt.android.plugin")
}

android {
	buildToolsVersion = "31.0.0"
	compileSdk = 31
	
	defaultConfig {
		applicationId = "br.com.luizrcs.correiostracker"
		
		minSdk = 21
		targetSdk = 31
		
		versionCode = 1
		versionName = "1.0.0"
	}
	
	buildFeatures {
		compose = true
		viewBinding = true
	}
	
	buildTypes {
		getByName("release") {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}
	
	compileOptions {
		sourceCompatibility = VERSION_1_8
		targetCompatibility = VERSION_1_8
	}
	
	kotlinOptions {
		apiVersion = "1.6"
		languageVersion = "1.6"
		
		freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
	}
	
	composeOptions {
		kotlinCompilerExtensionVersion = "1.1.0-alpha06"
	}
}

repositories {
	maven("https://dl.bintray.com/kotlin/kotlinx")
}

dependencies {
	kotlin("stdlib-jdk8", Versions.kotlin)
	
	androidx("appcompat", Versions.appCompat)
	androidx("cardview", Versions.cardView)
	androidxKtx("collection", Versions.collectionKtx)
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
	compose("material")
	compose("ui", name = "tooling")
	androidx("activity", "compose", Versions.activityCompose)
	androidx("lifecycle", "viewmodel-compose", Versions.lifecycleViewModelCompose)
	
	implementation("com.google.android.material", "material", Versions.material)
	
	implementation("io.coil-kt", "coil", Versions.coil)
	
	implementation("com.github.salomonbrys.kotson", "kotson", Versions.kotson)
	implementation("com.google.code.gson", "gson", Versions.gson)
	
	implementation("joda-time", "joda-time", Versions.jodaTime)
	
	okHttp("logging-interceptor", Versions.okHttp)
	retrofit("converter-gson", Versions.retrofit)
	retrofit(Versions.retrofit)
	
	implementation("com.google.dagger", "hilt-android", Versions.hilt)
	kapt("com.google.dagger", "hilt-android-compiler", Versions.hilt)
}
