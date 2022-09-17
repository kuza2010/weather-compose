package ru.adanil.weather.ui.screens.home

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope

class WeatherPoints(
    canvasDrawScope: DrawScope,
    private val temperatureList: List<Float>
) {
    val points = getPointsForCanvas(canvasDrawScope)

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
        val columnHeight = canvasSize.height * 0.8f
        val columnWidth = canvasSize.width / temperatureList.lastIndex

        val weatherGraphPoints = mutableListOf<Offset>()

        weatherGraphPoints.add(Offset(0f, columnHeight * (1f - (temperatureList[0] / maxT))))
        (1 until temperatureList.size).map { i ->
            val point = Offset(i * columnWidth, columnHeight * (1f - (temperatureList[i] / maxT)))
            weatherGraphPoints.add(point)
        }
        weatherGraphPoints.add(Offset(canvasSize.width, canvasSize.height))
        weatherGraphPoints.add(Offset(0f, canvasSize.height))

        return weatherGraphPoints
    }
}