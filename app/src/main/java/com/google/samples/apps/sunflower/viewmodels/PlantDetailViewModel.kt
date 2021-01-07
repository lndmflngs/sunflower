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

package com.google.samples.apps.sunflower.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.validator.Validator
import com.example.core.wrappers.BuildConfigWrapper
import com.google.samples.apps.sunflower.data.databse.repository.GardenPlantingRepository
import com.google.samples.apps.sunflower.data.databse.repository.PlantRepository
import com.google.samples.apps.sunflower.fragment.PlantDetailFragment
import kotlinx.coroutines.launch

/**
 * The ViewModel used in [PlantDetailFragment].
 */
class PlantDetailViewModel constructor(
	plantRepository: PlantRepository,
	private val gardenPlantingRepository: GardenPlantingRepository,
	private val keyValidator: Validator<String>,
	private val buildConfigWrapper: BuildConfigWrapper,
	private val plantId: String,
) : ViewModel() {

	val isPlanted = gardenPlantingRepository.isPlanted(plantId).asLiveData()
	val plant = plantRepository.getPlant(plantId).asLiveData()

	fun addPlantToGarden() {
		viewModelScope.launch {
			gardenPlantingRepository.createGardenPlanting(plantId)
		}
	}

	fun hasValidUnsplashKey() = keyValidator.isValid(buildConfigWrapper.unsplashAccessKey)

	interface AssistedFactory {

		fun create(plantId: String): PlantDetailViewModel
	}

	companion object {

		fun provideFactory(
			assistedFactory: AssistedFactory,
			plantId: String,
		): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
			@Suppress("UNCHECKED_CAST")
			override fun <T : ViewModel?> create(modelClass: Class<T>): T {
				return assistedFactory.create(plantId) as T
			}
		}
	}
}
