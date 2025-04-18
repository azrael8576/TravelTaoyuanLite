package com.wei.traveltaoyuanlite.core.data.model

import com.wei.traveltaoyuanlite.core.model.data.Link
import com.wei.traveltaoyuanlite.core.network.model.wrapper.NetworkLink

fun NetworkLink.asExternalModel() = Link(
    src = this.src,
    subject = this.subject,
)
