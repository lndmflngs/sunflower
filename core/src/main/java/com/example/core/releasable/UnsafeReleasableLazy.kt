@file:Suppress("UNCHECKED_CAST")

package com.example.core.releasable

import com.example.core.feature.Releasable
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class UnsafeReleasableLazy<T : Any>(
	private val initializer: () -> T,
) : ReadOnlyProperty<Any?, T>, Releasable {

	private var releasableValue: Any = UNINITIALIZED_VALUE

	override fun getValue(thisRef: Any?, property: KProperty<*>): T {
		if (releasableValue === UNINITIALIZED_VALUE) {
			releasableValue = initializer()
		}

		return releasableValue as T
	}

	override fun release() {
		if (releasableValue === UNINITIALIZED_VALUE) {
			return
		}

		releasableValue = UNINITIALIZED_VALUE
	}

}