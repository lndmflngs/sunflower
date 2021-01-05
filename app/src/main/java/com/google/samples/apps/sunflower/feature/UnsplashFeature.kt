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

package com.google.samples.apps.sunflower.feature

import com.google.samples.apps.sunflower.api.UnsplashService
import com.google.samples.apps.sunflower.data.remote.repository.UnsplashRepository
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BASIC
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

class UnsplashFeature : Feature {

	val unsplashRepository by lazy { UnsplashRepository(unsplashService) }

	private val loggingInterceptor: Interceptor by lazy {
		HttpLoggingInterceptor().apply { level = BASIC }
	}

	private val converterFactory: Converter.Factory by lazy {
		GsonConverterFactory.create()
	}

	private val unsplashService: UnsplashService
		get() = UnsplashService.create(loggingInterceptor, converterFactory)
}
