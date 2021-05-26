import org.gradle.api.JavaVersion.*

plugins {
	`android-application`
	`kotlin-android`
	`kotlin-android-extensions`
	
	id("androidx.navigation.safeargs.kotlin")
}

android {
	buildToolsVersion = "30.0.3"
	compileSdkVersion = "android-30"
	
	defaultConfig {
		applicationId = "br.com.luizrcs.correiostracker"
		
		minSdkVersion(21)
		targetSdkVersion(30)
		
		versionCode = 1
		versionName = "1.0.0"
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
		freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
	}
}

repositories {
	maven("https://dl.bintray.com/kotlin/kotlinx")
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
	
	implementation("androidx.appcompat:appcompat:$appCompatVersion")
	implementation("androidx.collection:collection-ktx:$collectionKtxVersion")
	implementation("androidx.constraintlayout:constraintlayout:$constraintLayoutVersion")
	implementation("androidx.core:core-ktx:$coreKtxVersion")
	implementation("androidx.fragment:fragment-ktx:$fragmentKtxVersion")
	implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleKtxVersion")
	implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleKtxVersion")
	implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleKtxVersion")
	implementation("androidx.navigation:navigation-runtime-ktx:$navigationKtxVersion")
	implementation("androidx.navigation:navigation-fragment-ktx:$navigationKtxVersion")
	implementation("androidx.navigation:navigation-ui-ktx:$navigationKtxVersion")
	implementation("androidx.palette:palette-ktx:$paletteKtxVersion")
	implementation("androidx.room:room-ktx:$roomKtxVersion")
	implementation("androidx.work:work-runtime-ktx:$workRuntimeKtx")
	
	implementation("com.google.android.material:material:$materialVersion")
	
	implementation("com.github.salomonbrys.kotson", "kotson", kotsonVersion)
	implementation("com.google.code.gson", "gson", gsonVersion)
	
	implementation("com.squareup.retrofit2", "converter-gson", retrofitVersion)
	implementation("com.squareup.retrofit2", "retrofit", retrofitVersion)
}
