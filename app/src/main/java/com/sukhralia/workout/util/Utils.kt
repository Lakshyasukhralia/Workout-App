package com.sukhralia.workout.util

fun String.convertToNumberedPoints(): String {
    val lines = this.trimIndent().lines()
    val numberedPoints = StringBuilder()

    for ((index, line) in lines.withIndex()) {
        val pointNumber = index + 1
        numberedPoints.append("$pointNumber. $line\n\n")
    }

    return numberedPoints.toString().trim()
}