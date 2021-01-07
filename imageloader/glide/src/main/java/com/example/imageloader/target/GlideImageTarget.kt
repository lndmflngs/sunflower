package com.example.imageloader.target

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.request.transition.Transition
import com.example.imageloader.target.image.GlideImageCustomTarget

internal class GlideImageTarget(
	override val targetView: ImageView,
	private vararg val callbacks: Target,
) : GlideImageCustomTarget(targetView), ViewTarget<ImageView> {

	override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
		onSuccess(resource)
		callbacks.iterator().forEach { target -> target.onSuccess(resource) }
	}

	override fun onResourceCleared(placeholder: Drawable?) {
		onStart(placeholder)
		callbacks.iterator().forEach { target -> target.onStart(placeholder) }
	}

	override fun onLoadFailed(errorDrawable: Drawable?) {
		onError(errorDrawable)
		callbacks.iterator().forEach { target -> target.onError(errorDrawable) }
	}

}