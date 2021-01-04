/*
 * Copyright 2020 Google LLC
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

package com.google.samples.apps.sunflower.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.samples.apps.sunflower.adapters.diffutil.GalleryDiffCallback
import com.google.samples.apps.sunflower.adapters.holder.GalleryViewHolder
import com.google.samples.apps.sunflower.data.remote.model.UnsplashPhoto
import com.google.samples.apps.sunflower.databinding.ListItemPhotoBinding
import com.google.samples.apps.sunflower.fragment.GalleryFragment

/**
 * Adapter for the [RecyclerView] in [GalleryFragment].
 */

internal class GalleryAdapter : PagingDataAdapter<UnsplashPhoto, GalleryViewHolder>(
	GalleryDiffCallback()
) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
		return GalleryViewHolder(
			ListItemPhotoBinding.inflate(
				LayoutInflater.from(parent.context),
				parent,
				false
			)
		)
	}

	override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
		val photo = getItem(position)
		checkNotNull(photo) {
			return
		}

		holder.bind(photo)
	}

}