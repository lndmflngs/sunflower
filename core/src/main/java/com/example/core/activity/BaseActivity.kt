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

package com.example.core.activity

import android.app.Activity
import com.example.core.feature.Feature
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.javaType

open class BaseActivity : Activity() {

	inline fun <reified T : Feature> getFeature(): T {
		val declaredMemberProperties = application::class::declaredMemberProperties
		val features = declaredMemberProperties.get().map { it.returnType.javaType to it }.toMap()
		return (features[T::class.javaObjectType] as KProperty1<Any, *>).get(application) as T
	}

}