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

package com.google.samples.apps.sunflower.feature

import android.content.Context
import com.example.core.feature.Feature
import com.google.samples.apps.sunflower.data.databse.AppDatabase
import com.google.samples.apps.sunflower.data.databse.dao.GardenPlantingDao
import com.google.samples.apps.sunflower.data.databse.dao.PlantDao

class DatabaseFeature(
	private val context: Context,
) : Feature {

	companion object {

		const val DATABASE_NAME = "sunflower-db"
	}

	val appDatabase: AppDatabase
		get() = AppDatabase.getInstance(context)

	val plantDao: PlantDao
		get() = appDatabase.plantDao()

	val gardenPlantingDao: GardenPlantingDao
		get() = appDatabase.gardenPlantingDao()
}