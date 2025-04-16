package com.wei.traveltaoyuanlite.core.data.model

import com.wei.traveltaoyuanlite.core.model.data.Image
import com.wei.traveltaoyuanlite.core.network.model.wrapper.NetworkImage

fun NetworkImage.asExternalModel() = Image(
    src = this.src,
    subject = this.subject,
    ext = this.ext,
)
