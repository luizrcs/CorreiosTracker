package br.com.luizrcs.correiostracker.ui.util.extensions

import android.os.Build.VERSION.*
import br.com.luizrcs.correiostracker.*

val isDebug = BuildConfig.DEBUG

fun versionIsAtLeast(versionCode: Int) = SDK_INT >= versionCode