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

package com.example.core.lazy

import java.io.Serializable

internal class SynchronizedMutableLazyImpl<T>(
	private val initializer: () -> T,
	lock: Any? = null,
) : MutableLazy<T>, Serializable {

	@Volatile
	private var _value: Any? = UNINITIALIZED_VALUE

	// final field is required to enable safe publication of constructed instance
	private val lock = lock ?: this

	override val value: T
		get() {
			val _v1 = _value

			if (_v1 !== UNINITIALIZED_VALUE) {
				return _v1 as T
			}

			return synchronized(lock) {
				val _v2 = _value

				if (_v2 !== UNINITIALIZED_VALUE) {
					_v2 as T
				} else {
					val typedValue = initializer()
					_value = typedValue
					typedValue
				}
			}
		}

	override fun isInitialized(): Boolean = _value !== UNINITIALIZED_VALUE

	override fun toString(): String = if (isInitialized()) {
		value.toString()
	} else {
		LAZY_NOT_INIT_MESSAGE
	}

	override fun setUninitialized() {
		_value = UNINITIALIZED_VALUE
	}

	private fun writeReplace(): Any = InitializedMutableLazyImpl(value)
}