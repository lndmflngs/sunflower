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

package com.example.core.fragment

import android.app.Application
import androidx.fragment.app.Fragment
import com.example.core.extensions.getFeature
import com.example.core.extensions.releaseAllFeatures
import com.example.core.extensions.releaseFeature
import com.example.core.feature.Feature
import com.example.core.feature.ReleasableFeature

open class BaseFragment : Fragment() {

	protected val application: Application
		get() = requireContext().applicationContext as Application

	protected inline fun <reified T : Feature> getFeature(): T {
		return application.getFeature()
	}

	protected inline fun <reified T : ReleasableFeature> releaseFeature() {
		application.releaseFeature<T>()
	}

	protected fun releaseAllFeatures() {
		application.releaseAllFeatures()
	}

}