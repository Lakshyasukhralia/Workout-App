package com.sukhralia.workout.feature.workout.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sukhralia.workout.feature.workout.domain.model.Exercise
import java.util.Date

@Entity("workout_history")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(defaultValue = "0") val uid: Int? = null,
    @ColumnInfo(name = "id") val id: String? = null,
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "workout_id") val workoutId: String? = null,
    @ColumnInfo(name = "instruction") val instruction: String? = null,
    @ColumnInfo(name = "demo_img") val demoImg: String? = null,
    @ColumnInfo(name = "create_date") val createDate: Date = Date(),
    @ColumnInfo(name = "wgt") val wgt: Int = 0,
    @ColumnInfo(name = "reps") val reps: Int = 0
) : java.io.Serializable

fun ExerciseEntity.toExercise() = Exercise(
    id = id,
    name = name,
    description = description,
    workoutId = workoutId,
    instruction = instruction,
    demoImg = demoImg,
    createDate = createDate,
    wgt = wgt,
    reps = reps
)

