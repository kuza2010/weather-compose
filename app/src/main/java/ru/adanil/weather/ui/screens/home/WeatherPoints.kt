package ru.adanil.weather.ui.screens.home

import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path

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

    val points by lazy {
        getPointsForCanvas(canvasSize)
    }
    val graphPoints
        // exclude last two path points
        get() = points.subList(0, points.size - 2)

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
        val maxT = temperatureList.max()
        val columnHeight = canvasSize.height
        val columnWidth = canvasSize.width / temperatureList.size

        val weatherGraphPoints = mutableListOf<Offset>()

        weatherGraphPoints.add(
            Offset(
                x = 0f,
                y = calcY(columnHeight, maxT, temperatureList[0])
            )
        )
        weatherGraphPoints.add(
            Offset(
                x = columnWidth,
                y = calcY(columnHeight, maxT, temperatureList[0])
            )
        )
        (1 until temperatureList.size).map { i ->
            val point = Offset(
                x = calcX(columnWidth, i),
                y = calcY(columnHeight, maxT, temperatureList[i])
            )
            weatherGraphPoints.add(point)
        }
        weatherGraphPoints.add(Offset(canvasSize.width, canvasSize.height))
        weatherGraphPoints.add(Offset(0f, canvasSize.height))

        Log.d("WeatherPoints", "points: $weatherGraphPoints")

        return weatherGraphPoints
    }

    private fun calcX(columnWidth: Float, pointIndex: Int): Float = (pointIndex + 1) * columnWidth

    private fun calcY(columnHeight: Float, maxTemperature: Float, currentTemperature: Float) =
        columnHeight * (1f - currentTemperature / maxTemperature)
}
