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

import android.graphics.drawable.Drawable
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import com.example.imageloader.ImageLoader
import com.example.imageloader.request.RequestBuilder
import com.example.imageloader.target.Target
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.samples.apps.sunflower.extensions.sunflowerApplication

private val View.imageLoader: ImageLoader
	get() = sunflowerApplication.imageFeature.imageLoader

fun View.bindIsGone(isGone: Boolean) = with(this) {
	visibility = if (isGone) {
		View.GONE
	} else {
		View.VISIBLE
	}
}

fun ImageView.bindImageFromUrl(imageUrl: String?) {
	// TODO: Remove Logger callback
	if (! imageUrl.isNullOrEmpty()) {
		val request = RequestBuilder {
			image(imageUrl)
			addCallback(object : Target {
				override fun onError(error: Drawable?) {
					super.onError(error)
					Log.d("imageFromUrl", "error: $error")
				}

				override fun onStart(placeholder: Drawable?) {
					super.onStart(placeholder)
					Log.d("imageFromUrl", "onStart: $placeholder")
				}

				override fun onSuccess(result: Drawable) {
					super.onSuccess(result)
					Log.d("imageFromUrl", "onSuccess: $result")
				}
			})
		}.build()

		imageLoader.execute(request, this)
	}
}

fun FloatingActionButton.bindIsFabGone(isGone: Boolean?) = with(this) {
	if (isGone == null || isGone) {
		hide()
	} else {
		show()
	}
}

fun TextView.bindRenderHtml(description: String?) = with(this) {
	if (description != null) {
		text = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
		movementMethod = LinkMovementMethod.getInstance()
	} else {
		text = ""
	}
}