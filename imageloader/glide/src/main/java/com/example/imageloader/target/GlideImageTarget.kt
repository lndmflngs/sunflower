package com.example.imageloader.target

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.request.transition.Transition
import com.example.imageloader.target.image.GlideImageCustomTarget

internal class GlideImageTarget(
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