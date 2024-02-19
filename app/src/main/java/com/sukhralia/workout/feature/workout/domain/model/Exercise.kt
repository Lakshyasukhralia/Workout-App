package com.sukhralia.workout.feature.workout.domain.model

data class Exercise(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val images: List<String>? = null,
    val categoryId: String? = null,
    val instruction: String? = null,
    val note: String? = null,
)
