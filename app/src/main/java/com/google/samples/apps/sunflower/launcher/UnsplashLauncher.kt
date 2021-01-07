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

package com.google.samples.apps.sunflower.launcher

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.core.launcher.ContextLauncher
import com.example.core.validator.Validator
import com.google.samples.apps.sunflower.launcher.args.UnsplashArgs

class UnsplashLauncher(
	context: Context,
	private val urlValidator: Validator<String>,
) : ContextLauncher<Context, UnsplashArgs>(context) {

	private val String.isValidUrl: Boolean
		get() = urlValidator.isValid(this)

	override fun launch(launchArgs: UnsplashArgs) = with(context) {
		val intent = buildIntent(launchArgs)
		startActivity(intent)
	}

	override fun buildIntent(launchArgs: UnsplashArgs): Intent = with(launchArgs) {
		if (! photoUrl.isValidUrl) {
			return EMPTY_INTENT
		}

		val uri = Uri.parse(photoUrl)
		return Intent(Intent.ACTION_VIEW, uri)
	}

}
