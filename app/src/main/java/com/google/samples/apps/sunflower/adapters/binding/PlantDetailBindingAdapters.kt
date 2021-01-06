/*
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower.adapters.binding

import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.databinding.BindingAdapter
import com.example.imageloader.ImageLoader
import com.example.imageloader.request.RequestBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.extensions.sunflowerApplication
import com.google.samples.apps.sunflower.wrappers.ResourceReader

private val View.imageLoader: ImageLoader
	get() = sunflowerApplication.imageFeature.imageLoader

private val View.resourceReader: ResourceReader
	get() = sunflowerApplication.wrappersFeature.resourceReader

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
	if (! imageUrl.isNullOrEmpty()) {
		val request = RequestBuilder {
			image(imageUrl)
		}.build()

		view.imageLoader.execute(request, view)
//		TODO: transition(DrawableTransitionOptions.withCrossFade())
	}
}

@BindingAdapter("isFabGone")
fun bindIsFabGone(view: FloatingActionButton, isGone: Boolean?) = with(view) {
	if (isGone == null || isGone) {
		hide()
	} else {
		show()
	}
}

@BindingAdapter("renderHtml")
fun bindRenderHtml(view: TextView, description: String?) = with(view) {
	if (description != null) {
		text = HtmlCompat.fromHtml(description, FROM_HTML_MODE_COMPACT)
		movementMethod = LinkMovementMethod.getInstance()
	} else {
		text = ""
	}
}

@BindingAdapter("wateringText")
fun bindWateringText(textView: TextView, wateringInterval: Int) = with(textView) {
	val quantityString = resourceReader.quantityString(
		R.plurals.watering_needs_suffix,
		wateringInterval,
		wateringInterval
	)

	text = quantityString
}