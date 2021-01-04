/*
 * Copyright 2020 Google LLC
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

package com.google.samples.apps.sunflower.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.google.samples.apps.sunflower.data.remote.model.UnsplashPhoto
import com.google.samples.apps.sunflower.data.remote.repository.UnsplashRepository
import kotlinx.coroutines.flow.Flow

class GalleryViewModel(
	private val repository: UnsplashRepository,
) : ViewModel() {

	private var currentQueryValue: String? = null
	private var currentSearchResult: Flow<PagingData<UnsplashPhoto>>? = null

	fun searchPictures(queryString: String): Flow<PagingData<UnsplashPhoto>> {
		currentQueryValue = queryString
		val newResult = getSearchResultStream(queryString)
		currentSearchResult = newResult
		return newResult
	}

	private fun getSearchResultStream(queryString: String): Flow<PagingData<UnsplashPhoto>> {
		return repository.getSearchResultStream(queryString).cachedIn(viewModelScope)
	}
}
