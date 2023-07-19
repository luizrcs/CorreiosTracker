package br.com.luizrcs.correiostracker.ui.util.extensions

import android.os.Build.VERSION.*

val isDebug = true // TODO: BuildConfig.DEBUG

fun versionIsAtLeast(versionCode: Int) = SDK_INT >= versionCode