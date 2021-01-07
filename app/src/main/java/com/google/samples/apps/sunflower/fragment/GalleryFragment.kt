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

package com.google.samples.apps.sunflower.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.core.extensions.lazyViewModel
import com.example.core.fragment.BaseFragment
import com.google.samples.apps.sunflower.adapters.GalleryAdapter
import com.google.samples.apps.sunflower.databinding.FragmentGalleryBinding
import com.google.samples.apps.sunflower.feature.LaunchersFeature
import com.google.samples.apps.sunflower.feature.UnsplashFeature
import com.google.samples.apps.sunflower.viewmodels.GalleryViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class GalleryFragment : BaseFragment() {

	private val args: GalleryFragmentArgs by navArgs()

	private val adapter = GalleryAdapter(getFeature<LaunchersFeature>().unsplashLauncher)

	private var searchJob: Job? = null

	private val viewModel: GalleryViewModel by lazyViewModel {
		GalleryViewModel(getFeature<UnsplashFeature>().unsplashRepository)
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		val binding = FragmentGalleryBinding.inflate(inflater, container, false)
		context ?: return binding.root

		with(binding) {
			initAdapter(this)
			initToolbar(this)
		}

		search(args.plantName)

		return binding.root
	}

	private fun initToolbar(binding: FragmentGalleryBinding) = with(binding) {
		toolbar.setNavigationOnClickListener { view ->
			view.findNavController().navigateUp()
		}
	}

	private fun initAdapter(binding: FragmentGalleryBinding) = with(binding) {
		photoList.adapter = adapter
	}

	private fun search(query: String) {
		// Make sure we cancel the previous job before creating a new one
		searchJob?.cancel()
		searchJob = lifecycleScope.launch {
			viewModel.searchPictures(query).collectLatest {
				adapter.submitData(it)
			}
		}
	}
}

