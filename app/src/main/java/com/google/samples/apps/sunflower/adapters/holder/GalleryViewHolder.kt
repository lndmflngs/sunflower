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

package com.google.samples.apps.sunflower.adapters.holder

import androidx.recyclerview.widget.RecyclerView
import com.google.samples.apps.sunflower.data.remote.model.UnsplashPhoto
import com.google.samples.apps.sunflower.databinding.ListItemPhotoBinding
import com.google.samples.apps.sunflower.launcher.UnsplashLauncher
import com.google.samples.apps.sunflower.launcher.args.UnsplashArgs

internal class GalleryViewHolder(
	private val binding: ListItemPhotoBinding,
	private val unsplashLauncher: UnsplashLauncher,
) : RecyclerView.ViewHolder(binding.root) {

	init {
		binding.setClickListener { openPhoto(binding.photo) }
	}

	fun bind(item: UnsplashPhoto) = binding.apply {
		photo = item
		executePendingBindings()
	}

	private fun openPhoto(photo: UnsplashPhoto?) {
		checkNotNull(photo) {
			return
		}

		val unsplashArgs = UnsplashArgs(photo.user.attributionUrl)
		unsplashLauncher.launch(unsplashArgs)
	}
}