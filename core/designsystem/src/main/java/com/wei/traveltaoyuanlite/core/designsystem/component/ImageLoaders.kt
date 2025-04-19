package com.wei.traveltaoyuanlite.core.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.ImageLoader
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest

/**
 * A transparent Painter that does not draw anything.
 */
object TransparentPainter : Painter() {
    override val intrinsicSize: Size
        get() = Size.Unspecified

    override fun DrawScope.onDraw() {
        // No drawing action, resulting in a transparent painter
    }
}

data class CoilImageState(
    val painter: Painter,
    val loadingState: LoadingState,
)

enum class LoadingState {
    Loading,
    Success,
    Error,
    Empty,
}

/**
 * Creates a Coil-based image painter with loading state support.
 *
 * This composable provides a unified way to load images from various sources (e.g., drawable resource IDs, URLs),
 * with support for preview mode, SVG decoding, and loading state introspection. It returns a [CoilImageState]
 * containing both the [Painter] and the current [AsyncImagePainter.State] to enable UI reactions
 * based on loading progress.
 *
 * @param imageData The source of the image to load. Accepts resource ID [Int] or a URL [String]/[Uri].
 * @param isPreview If true, skips loading and returns a placeholder for design-time preview.
 * @param reviewResId Optional fallback drawable resource used in preview mode.
 * @return A [CoilImageState] which contains both the painter and the current loading state.
 */
@Composable
fun coilImagePainter(
    imageData: Any,
    isPreview: Boolean = false,
    @DrawableRes reviewResId: Int? = null,
): CoilImageState {
    val context = LocalContext.current

    // For preview mode, return the provided resource or a transparent painter
    if (isPreview) {
        val painter = reviewResId?.let { painterResource(id = it) } ?: TransparentPainter
        return CoilImageState(
            painter = painter,
            // Treat preview as loaded
            loadingState = LoadingState.Success,
        )
    }

    // If the image source is a drawable resource ID, return it directly
    if (imageData is Int) {
        val painter = painterResource(id = imageData)
        return CoilImageState(
            painter = painter,
            loadingState = LoadingState.Success,
        )
    }

    // For URL or other sources, use Coil with custom SVG support
    val imageLoader = remember {
        ImageLoader.Builder(context)
            .components { add(SvgDecoder.Factory()) }
            .build()
    }

    val request = ImageRequest.Builder(context)
        .data(imageData)
        .crossfade(true)
        .build()

    val painter = rememberAsyncImagePainter(model = request, imageLoader = imageLoader)

    val loadingState = when (painter.state) {
        is AsyncImagePainter.State.Loading -> LoadingState.Loading
        is AsyncImagePainter.State.Success -> LoadingState.Success
        is AsyncImagePainter.State.Error -> LoadingState.Error
        is AsyncImagePainter.State.Empty -> LoadingState.Empty
    }

    return CoilImageState(
        painter = painter,
        loadingState = loadingState,
    )
}
