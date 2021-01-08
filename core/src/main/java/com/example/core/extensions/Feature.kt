@file:Suppress("UNCHECKED_CAST")
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

package com.example.core.extensions

import android.app.Application
import com.example.core.feature.Feature
import com.example.core.feature.ReleasableFeature
import com.example.core.lazy.MutableLazy
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.javaType

inline fun <reified T : Feature> Application.getProperty(): KProperty1<Application, T> {
	val features = this::class.declaredMemberProperties
		.map { it.returnType.javaType to it }
		.toMap()

	return features[T::class.javaObjectType] as KProperty1<Application, T>
}

inline fun <reified T : Feature> Application.getFeature(): T {
	return getProperty<T>().get(this)
}

inline fun <reified T : ReleasableFeature> Application.releaseFeature() = with(getProperty<T>()){
	isAccessible = true

	val delegate = getDelegate(this@releaseFeature) as MutableLazy<T>
	delegate.setUninitialized()

	isAccessible = false
}