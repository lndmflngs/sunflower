package com.example.imageloader.request

import com.example.imageloader.options.ImageOptions
import com.example.imageloader.target.Target

class Request(
	val imageOptions: ImageOptions,
	val imageCallback: Array<Target>,
)