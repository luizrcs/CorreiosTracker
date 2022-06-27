package br.com.luizrcs.correiostracker.ui.util.extensions

import android.content.*
import android.widget.Toast.*

fun showToast(context: Context, text: String, duration: Int = LENGTH_SHORT) = makeText(context, text, duration).show()
fun showShortToast(context: Context, text: String) = showToast(context, text, LENGTH_SHORT)
fun showLongToast(context: Context, text: String) = showToast(context, text, LENGTH_LONG)