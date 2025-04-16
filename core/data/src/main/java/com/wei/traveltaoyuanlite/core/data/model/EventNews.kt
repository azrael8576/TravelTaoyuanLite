package com.wei.traveltaoyuanlite.core.data.model

import com.wei.traveltaoyuanlite.core.model.data.EventNews
import com.wei.traveltaoyuanlite.core.network.model.NetworkEventNews

fun NetworkEventNews.asExternalModel() = EventNews(
    id = this.id,
    tyWebsite = this.tyWebsite,
    classes = this.classes?.classes ?: emptyList(),
    name = this.name,
    description = this.description,
    start = this.start ?: "",
    end = this.end ?: "",
    images = this.images?.images?.map { it.asExternalModel() } ?: emptyList(),
    files = this.files?.files?.map { it.asExternalModel() } ?: emptyList(),
    links = this.links?.links?.map { it.asExternalModel() } ?: emptyList(),
    posted = this.posted,
    modified = this.modified,
)
