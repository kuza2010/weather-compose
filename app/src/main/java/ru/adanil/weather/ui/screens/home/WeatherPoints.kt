package ru.adanil.weather.ui.screens.home

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import kotlin.math.abs

/**
 * Weather graph represented as a path figure.
 */
class WeatherPoints(
    private val canvasSize: Size,
    val temperatureList: List<Float>,
) {
    companion object {
        const val MIN_COLLECTION_SIZE = 2
    }

    private val points by lazy {
        getPointsForCanvas(canvasSize)
    }
    val graphPoints by lazy {
        // exclude last two path points
        points.subList(0, points.size - 2)
    }

    init {
        require(temperatureList.size >= MIN_COLLECTION_SIZE)
    }

    fun getPath(): Path {
        return Path().apply {
            moveTo(points[0].x, points[0].y)
            points.forEach { point -> lineTo(point.x, point.y) }
            close()
        }
    }

    private fun getPointsForCanvas(canvasSize: Size): List<Offset> {
        val scaledTemperature = getScaledTemperature()
        val maxT = scaledTemperature.max()
        val columnHeight = canvasSize.height
        val columnWidth = canvasSize.width / temperatureList.size

        val weatherGraphPoints = mutableListOf<Offset>()

        // add first points
        weatherGraphPoints.add(
            Offset(
                x = 0f,
                y = calcY(columnHeight, maxT, scaledTemperature[0])
            )
        )
        weatherGraphPoints.add(
            Offset(
                x = columnWidth,
                y = calcY(columnHeight, maxT, scaledTemperature[0])
            )
        )
        // add main graph points
        (1 until scaledTemperature.size).map { i ->
            val point = Offset(
                x = calcX(columnWidth, i),
                y = calcY(columnHeight, maxT, scaledTemperature[i])
            )
            weatherGraphPoints.add(point)
        }
        // add last points
        weatherGraphPoints.add(Offset(canvasSize.width, canvasSize.height))
        weatherGraphPoints.add(Offset(0f, canvasSize.height))
        return weatherGraphPoints
    }

    private fun getScaledTemperature(): List<Float> {
        val minT = temperatureList.min()
        return when {
            minT < 0f -> temperatureList.map { it + abs(minT) }
            else -> temperatureList
        }
    }

    private fun calcX(columnWidth: Float, pointIndex: Int): Float = (pointIndex + 1) * columnWidth

    private fun calcY(columnHeight: Float, maxTemperature: Float, currentTemperature: Float) =
        columnHeight * (1f - currentTemperature / maxTemperature)
}
