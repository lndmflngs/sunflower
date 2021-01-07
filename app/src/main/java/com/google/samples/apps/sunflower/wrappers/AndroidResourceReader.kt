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
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.example.core.wrappers.ResourceReader

class AndroidResourceReader(
	private val context: Context,
) : ResourceReader {

	override fun color(
		@ColorRes
		res: Int,
	): Int = ContextCompat.getColor(context, res)

	override fun string(
		@StringRes
		res: Int,
	): String = context.getString(res)

	override fun string(
		@StringRes
		res: Int,
		vararg args: String,
	): String = context.getString(res, args)

	override fun quantityString(
		id: Int,
		quantity: Int,
	): String = context.resources.getQuantityString(id, quantity)

	override fun quantityString(
		id: Int,
		quantity: Int,
		vararg formatArgs: Any?,
	): String = context.resources.getQuantityString(id, quantity, formatArgs)

	override fun drawable(
		@DrawableRes
		res: Int,
	): Drawable? = AppCompatResources.getDrawable(context, res)

	override fun dimen(
		@DimenRes
		res: Int,
	): Number = context.resources.getDimension(res)

}