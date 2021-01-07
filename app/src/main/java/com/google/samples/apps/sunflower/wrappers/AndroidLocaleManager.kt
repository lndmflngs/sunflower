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

import android.content.Context
import android.content.res.Configuration
import androidx.core.os.ConfigurationCompat
import com.example.core.wrappers.LocaleManager
import java.util.*

class AndroidLocaleManager(
	private val context: Context,
) : LocaleManager {

	private val configuration: Configuration
		get() = context.applicationContext.resources.configuration

	override val currentLocale: Locale
		get() = ConfigurationCompat.getLocales(configuration).get(0)

	override fun createLocaleContext(context: Context, locale: Locale): Context {
		Locale.setDefault(locale)
		return createConfigurationContext(context, locale);
	}

	private fun createConfigurationContext(context: Context, locale: Locale): Context {
		val localeConfiguration = configuration.apply { setLocale(locale) }
		return context.createConfigurationContext(localeConfiguration)
	}

}