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

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.core.wrappers.ResourceReader
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.extensions.sunflowerApplication

private val View.resourceReader: ResourceReader
	get() = sunflowerApplication.wrappersFeature.resourceReader

@BindingAdapter("wateringText")
fun bindWateringText(textView: TextView, wateringInterval: Int) = with(textView) {
	val quantityString = resourceReader.quantityString(
		R.plurals.watering_needs_suffix,
		wateringInterval,
		wateringInterval
	)

	text = quantityString
}