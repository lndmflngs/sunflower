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

package com.example.core.releasable

import android.util.Log
import com.example.core.feature.Releasable
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class SynchronizedReleasableLazy<T : Any>(
	private val initializer: () -> T,
) : ReadOnlyProperty<Any?, T>, Releasable {

	@Volatile
	private var releasableValue: T? = initializer()

	@Synchronized
	override fun getValue(thisRef: Any?, property: KProperty<*>): T {
		if (releasableValue === null) {
			releasableValue = initializer()
		}
		return releasableValue as T
	}

	@Synchronized
	override fun release() {
		releasableValue = null
	}

}