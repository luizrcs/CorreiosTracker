package br.com.luizrcs.correiostracker.ui.util.extensions

import android.content.*
import android.util.*
import androidx.annotation.*

fun Context.getColorFromAttr(@AttrRes attr: Int) = TypedValue().also { theme.resolveAttribute(attr, it, true) }.data