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

package com.google.samples.apps.sunflower.wrappers

import com.example.core.wrappers.BuildConfigWrapper
import com.google.samples.apps.sunflower.BuildConfig

class SunflowerBuildConfigWrapper : BuildConfigWrapper {

	companion object {

		const val NULL: String = "null"
	}

	override val unsplashAccessKey: String
		get() {
			val accessKey = BuildConfig.UNSPLASH_ACCESS_KEY
			if (accessKey == NULL) {
				return ""
			}

			return accessKey
		}

	override val unsplashBaseUrl: String
		get() = BuildConfig.BASE_URL

	override val isDebug: Boolean
		get() = BuildConfig.BUILD_TYPE == "debug"

	override val isDev: Boolean
		get() = BuildConfig.FLAVOR == "dev"

}