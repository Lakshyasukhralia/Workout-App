package com.sukhralia.workout.feature.workout.domain.model


data class Workout(
    var id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val image: String? = null,
) : java.io.Serializable

