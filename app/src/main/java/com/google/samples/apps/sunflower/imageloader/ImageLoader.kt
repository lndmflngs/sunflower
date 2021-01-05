package com.google.samples.apps.sunflower.imageloader

import android.view.View
import com.google.samples.apps.sunflower.imageloader.request.Request
import com.google.samples.apps.sunflower.imageloader.target.ViewTarget

interface ImageLoader {

	fun <V : View> execute(request: Request, view: V)

	fun <T : View> buildViewTarget(view: T): ViewTarget<T>

}