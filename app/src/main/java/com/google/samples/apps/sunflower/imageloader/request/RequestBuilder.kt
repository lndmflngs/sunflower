package com.google.samples.apps.sunflower.imageloader.request

import com.google.samples.apps.sunflower.imageloader.options.ImageOptions

class RequestBuilder() {

	private var imageOptions: ImageOptions? = null

	constructor(init: RequestBuilder.() -> Unit) : this() {
		init()
	}

	fun image(init: ImageOptions.() -> Unit) {
		imageOptions = ImageOptions().apply { init() }
	}

	fun image(init: Any?) {
		image { data = init }
	}

	fun build(): Request {
		return Request(checkNotNull(imageOptions))
	}

}