package com.example.imageloader.request

import com.example.imageloader.options.ImageOptions
import com.example.imageloader.target.Target

class RequestBuilder() {

	private var imageOptions: ImageOptions? = null

	private val imageCallback: MutableList<Target> = mutableListOf()

	constructor(init: RequestBuilder.() -> Unit) : this() {
		init()
	}

	fun image(init: ImageOptions.() -> Unit) {
		imageOptions = ImageOptions().apply { init() }
	}

	fun image(init: Any?) {
		image { data = init }
	}

	fun addCallback(target: Target) {
		imageCallback.add(target)
	}

	fun build(): Request {
		return Request(
			imageOptions = checkNotNull(imageOptions),
			imageCallback = imageCallback.toTypedArray()
		)
	}

}