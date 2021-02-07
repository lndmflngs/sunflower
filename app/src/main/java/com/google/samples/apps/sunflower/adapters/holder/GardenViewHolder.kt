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

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.samples.apps.sunflower.data.databse.PlantAndGardenPlantings
import com.google.samples.apps.sunflower.databinding.ListItemGardenPlantingBinding
import com.google.samples.apps.sunflower.fragment.HomeViewPagerFragmentDirections.Companion.actionViewPagerFragmentToPlantDetailFragment

internal class GardenViewHolder(
	private val binding: ListItemGardenPlantingBinding,
) : RecyclerView.ViewHolder(binding.root) {

	init {
		binding.setClickListener { view -> navigateToPlant(binding.viewModel?.plantId, view) }
	}

	fun bind(plantings: PlantAndGardenPlantings) = with(binding) {
		viewModel = PlantAndGardenPlantingsViewModel(plantings)
		executePendingBindings()
	}

	private fun navigateToPlant(plantId: String?, view: View) {
		checkNotNull(plantId) {
			return
		}

		val direction = actionViewPagerFragmentToPlantDetailFragment(plantId)
		view.findNavController().navigate(direction)
	}

}