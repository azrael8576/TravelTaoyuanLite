package com.wei.traveltaoyuanlite.core.data.model

import com.wei.traveltaoyuanlite.core.model.data.File
import com.wei.traveltaoyuanlite.core.network.model.wrapper.NetworkFile

fun NetworkFile.asExternalModel() = File(
    src = this.src,
    subject = this.subject,
    ext = this.ext,
)
