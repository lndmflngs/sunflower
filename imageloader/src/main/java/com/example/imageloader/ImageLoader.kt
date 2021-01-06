package com.example.imageloader

import android.view.View
import com.example.imageloader.request.Request
import com.example.imageloader.target.ViewTarget

interface ImageLoader {

	fun <V : View> execute(request: Request, view: V)

	fun <T : View> buildViewTarget(view: T): ViewTarget<T>

}