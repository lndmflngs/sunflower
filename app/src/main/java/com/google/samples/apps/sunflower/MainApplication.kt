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

package com.google.samples.apps.sunflower

import android.app.Application
import com.example.core.extensions.mutableLazy
import com.google.samples.apps.sunflower.feature.DatabaseFeature
import com.google.samples.apps.sunflower.feature.ImageFeature
import com.google.samples.apps.sunflower.feature.LaunchersFeature
import com.google.samples.apps.sunflower.feature.PlantFeature
import com.google.samples.apps.sunflower.feature.UnsplashFeature
import com.google.samples.apps.sunflower.feature.WrappersFeature

class MainApplication : Application(), SunflowerApplication {

	override val databaseFeature by lazy { DatabaseFeature(this) }

	override val unsplashFeature by lazy { UnsplashFeature() }

	override val plantFeature by mutableLazy {
		PlantFeature(databaseFeature.plantDao, databaseFeature.gardenPlantingDao)
	}

	override val wrappersFeature by lazy { WrappersFeature(this) }

	override val launchersFeature by lazy { LaunchersFeature(this) }

	override val imageFeature by lazy { ImageFeature(this) }

}
