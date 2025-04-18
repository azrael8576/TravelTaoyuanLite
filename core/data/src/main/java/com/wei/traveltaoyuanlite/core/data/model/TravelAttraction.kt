package com.wei.traveltaoyuanlite.core.data.model

import com.wei.traveltaoyuanlite.core.model.data.TravelAttraction
import com.wei.traveltaoyuanlite.core.network.model.NetworkTravelAttraction

fun NetworkTravelAttraction.asExternalModel() = TravelAttraction(
    id = this.id,
    tyWebsite = this.tyWebsite,
    classes = this.classes?.classes ?: emptyList(),
    name = this.name,
    description = this.description,
    zipcode = this.zipcode ?: "",
    district = this.district ?: "",
    address = this.address ?: "",
    eastLongitude = this.eastLongitude?.toDoubleOrNull() ?: 0.00,
    northLatitude = this.northLatitude?.toDoubleOrNull() ?: 0.00,
    phone = this.phone ?: "",
    fax = this.fax ?: "",
    email = this.email ?: "",
    openTime = this.openTime ?: "",
    ticket = this.ticket ?: "",
    remind = this.remind ?: "",
    parking = this.parking ?: "",
    facilities = this.facilities?.facilities ?: emptyList(),
    images = this.images?.images?.map { it.asExternalModel() } ?: emptyList(),
    links = this.links?.links?.map { it.asExternalModel() } ?: emptyList(),
    posted = this.posted,
    modified = this.modified,
)
