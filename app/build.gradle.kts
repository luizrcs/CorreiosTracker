import org.gradle.api.JavaVersion.*

plugins {
	`android-application`
	`kotlin-android`
	kapt
	
	id("androidx.navigation.safeargs.kotlin")
	id("dagger.hilt.android.plugin")
}

android {
	buildToolsVersion = "32.0.0"
	compileSdk = 32
	
	defaultConfig {
		applicationId = "br.com.luizrcs.correiostracker"
		
		minSdk = 21
		targetSdk = 32
		
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
		apiVersion = "1.7"
		languageVersion = "1.7"
		
		freeCompilerArgs += listOf(
			"-Xopt-in=kotlin.RequiresOptIn",
			"-P",
			"plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=true"
		)
	}
	
	composeOptions {
		kotlinCompilerExtensionVersion = "1.2.0-beta02"
	}
}

repositories {
	maven("https://dl.bintray.com/kotlin/kotlinx")
}

dependencies {
	kotlin("stdlib-jdk8", Versions.kotlin)
	
	androidx("appcompat", Versions.appCompat)
	androidx("cardview", Versions.cardView)
	androidxKtx("collection", Versions.collection)
	androidx("constraintlayout", Versions.constraintLayout)
	androidxKtx("core", Versions.coreKtx)
	androidxKtx("lifecycle", "livedata", Versions.lifecycle)
	androidxKtx("lifecycle", "runtime", Versions.lifecycle)
	androidxKtx("lifecycle", "viewmodel", Versions.lifecycle)
	androidxKtx("navigation", "fragment", Versions.navigation)
	androidxKtx("navigation", "runtime", Versions.navigation)
	androidxKtx("navigation", "ui", Versions.navigation)
	androidxKtx("palette", Versions.palette)
	androidx("recyclerview", Versions.recyclerView)
	androidx("swiperefreshlayout", Versions.swipeRefreshLayout)
	
	compose("animation")
	compose("material", name = "icons-extended")
	compose("material3", version = Versions.material3Compose)
	compose("ui", name = "tooling")
	androidx("activity", "compose", Versions.activityCompose)
	androidx("lifecycle", "viewmodel-compose", Versions.lifecycleViewModelCompose)
	androidx("navigation", "compose", Versions.navigation)
	
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
