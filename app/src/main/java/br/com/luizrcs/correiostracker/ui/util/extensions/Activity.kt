package br.com.luizrcs.correiostracker.ui.util.extensions

import android.animation.*
import android.app.*

private val argbEvaluator = ArgbEvaluator()

fun Activity.animateStatusBarColor(colorFrom: Int, colorTo: Int) {
	ValueAnimator.ofObject(argbEvaluator, colorFrom, colorTo).run {
		duration = 200L
		addUpdateListener { valueAnimator -> window.statusBarColor = valueAnimator.animatedValue as Int }
		start()
	}
}

fun Activity.animateNavigationBarColor(colorFrom: Int, colorTo: Int) {
	ValueAnimator.ofObject(argbEvaluator, colorFrom, colorTo).run {
		duration = 200L
		addUpdateListener { valueAnimator -> window.navigationBarColor = valueAnimator.animatedValue as Int }
		start()
	}
}