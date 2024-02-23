package com.sukhralia.workout.feature.workout.domain.model

import com.sukhralia.workout.feature.workout.data.local.entity.ExerciseEntity
import java.util.Date

data class Exercise(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val images: List<String>? = null,
    val workoutId: String? = null,
    val instruction: String? = null,
    val note: String? = null,
    val demoImg: String? = null,
    val createDate: Date? = null,
    var wgt: Int = 0,
    var reps: Int = 0,
    val demoVideo: String? = null,
) : java.io.Serializable

fun Exercise.toExerciseEntity() = ExerciseEntity(
    id = id,
    name = name,
    description = description,
    workoutId = workoutId,
    instruction = instruction,
    demoImg = demoImg,
    wgt = wgt,
    reps = reps,
    demoVideo = demoVideo
)