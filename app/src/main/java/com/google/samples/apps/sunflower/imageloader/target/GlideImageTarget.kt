package com.google.samples.apps.sunflower.imageloader.target

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.request.transition.Transition
import com.google.samples.apps.sunflower.imageloader.target.image.GlideImageCustomTarget

class GlideImageTarget(
	override val targetView: ImageView,
) : GlideImageCustomTarget(targetView), ViewTarget<ImageView> {

	override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
		onSuccess(resource)
	}

	override fun onResourceCleared(placeholder: Drawable?) {
		onStart(placeholder)
	}

	override fun onLoadFailed(errorDrawable: Drawable?) {
		onError(errorDrawable)
	}

}