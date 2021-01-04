/*
 * Copyright 2018 Google LLC
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
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.adapters.diffutil.GardenPlantDiffCallback
import com.google.samples.apps.sunflower.adapters.holder.GardenViewHolder
import com.google.samples.apps.sunflower.data.databse.PlantAndGardenPlantings

internal class GardenPlantingAdapter : ListAdapter<PlantAndGardenPlantings, GardenViewHolder>(
	GardenPlantDiffCallback()
) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GardenViewHolder {
		return GardenViewHolder(
			DataBindingUtil.inflate(
				LayoutInflater.from(parent.context),
				R.layout.list_item_garden_planting,
				parent,
				false
			)
		)
	}

	override fun onBindViewHolder(holder: GardenViewHolder, position: Int) {
		holder.bind(getItem(position))
	}

}