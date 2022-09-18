package ru.adanil.weather.ui.screens.home

import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope

/**
 * Weather graph represented as a path figure.
 */
class WeatherPoints(
    canvasDrawScope: DrawScope,
    private val temperatureList: List<Float>,
    private val heightRatio: Float = 0.8f
) {
    companion object {
        const val MIN_COLLECTION_SIZE = 2
    }

    val points = getPointsForCanvas(canvasDrawScope)
    val graphPoints
        // exclude last two path points
        get() = points.subList(0, points.size - 2)

    init {
        require(heightRatio > 0 && heightRatio <= 1)
        require(temperatureList.size >= MIN_COLLECTION_SIZE)
    }

    fun getPath(): Path {
        val path = Path()
        path.moveTo(points[0].x, points[0].y)
        points.subList(1, points.size).forEach { point -> path.lineTo(point.x, point.y) }
        path.close()
        return path
    }

    private fun getPointsForCanvas(canvasDrawScope: DrawScope): List<Offset> {
        val maxT = temperatureList.max()
        val canvasSize = canvasDrawScope.size
        val columnHeight = canvasSize.height * heightRatio
        val reserved = canvasSize.height - columnHeight
        val columnWidth = canvasSize.width / temperatureList.lastIndex

        val weatherGraphPoints = mutableListOf<Offset>()

        weatherGraphPoints.add(
            Offset(
                x = 0f,
                y = reserved + columnHeight * (1f - (temperatureList[0] / maxT))
            )
        )
        (1 until temperatureList.size).map { i ->
            val point = Offset(
                x = i * columnWidth,
                y = reserved + columnHeight * (1f - (temperatureList[i] / maxT))
            )
            weatherGraphPoints.add(point)
        }
        weatherGraphPoints.add(Offset(canvasSize.width, canvasSize.height))
        weatherGraphPoints.add(Offset(0f, canvasSize.height))

        Log.e("TESTIN", "getPointsForCanvas: $weatherGraphPoints")

        return weatherGraphPoints
    }
}
