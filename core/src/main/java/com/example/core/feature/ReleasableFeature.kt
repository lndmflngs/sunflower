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

package com.example.core.feature

import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

interface ReleasableFeature : Feature, Releasable {

	override fun release() {
		this::class.declaredMemberProperties.mapNotNull { it.getDelegate() }
			.filterIsInstance<Releasable>()
			.forEach { it.release() }
	}

	@Suppress("NOTHING_TO_INLINE", "UNCHECKED_CAST")
	private inline fun KProperty1<out ReleasableFeature, *>.getDelegate(): Any? {
		isAccessible = true
		val delegate = (this as KProperty1<Any, *>).getDelegate(this@ReleasableFeature)
		isAccessible = false

		return delegate
	}

}