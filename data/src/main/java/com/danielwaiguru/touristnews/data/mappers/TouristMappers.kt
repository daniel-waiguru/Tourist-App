package com.danielwaiguru.touristnews.data.mappers

import com.danielwaiguru.touristnews.data.models.dtos.TouristDto
import com.danielwaiguru.touristnews.database.entities.TouristEntity
import com.danielwaiguru.touristnews.domain.models.Tourist

internal fun TouristDto.toTouristEntity(): TouristEntity =
    TouristEntity(
        createdAt = createdAt,
        id = id,
        touristEmail = touristEmail,
        touristLocation = touristLocation,
        touristName = touristName
    )

internal fun TouristEntity.toTourist(): Tourist =
    Tourist(
        createdAt = createdAt,
        id = id,
        touristEmail = touristEmail,
        touristLocation = touristLocation,
        touristName = touristName
    )

internal fun TouristDto.toTourist(): Tourist =
    Tourist(
        createdAt = createdAt,
        id = id,
        touristEmail = touristEmail,
        touristLocation = touristLocation,
        touristName = touristName
    )