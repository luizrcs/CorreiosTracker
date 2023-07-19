plugins {
	id("com.android.application") version Versions.androidApplication apply false
	id("org.jetbrains.kotlin.android") version Versions.kotlin apply false
	id("org.jetbrains.kotlin.kapt") version Versions.kotlin apply false
	id("com.google.dagger.hilt.android") version Versions.hilt apply false
	id("androidx.navigation.safeargs.kotlin") version Versions.navigationSafeArgs apply false
}

buildscript {
	repositories {
		google()
		mavenCentral()
	}
	
	dependencies {
		classpath("com.android.tools.build:gradle:${Versions.androidApplication}")
		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
		classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationSafeArgs}")
	}
}

allprojects {
	repositories {
		google()
		mavenCentral()
	}
}

tasks.register("clean", Delete::class) {
	delete(rootProject.buildDir)
}
