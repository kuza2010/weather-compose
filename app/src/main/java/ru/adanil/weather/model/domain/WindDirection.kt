package ru.adanil.weather.model.domain

import androidx.annotation.DrawableRes
import ru.adanil.weather.R

/**
 * We use simplified wind direction (8 values)
 * Check img: https://dpva.ru/netcat_files/Image/GuideUnits/WindroseRuEng/Windros%D0%B5RuEng.jpg
 */
enum class WindDirection(
    val degrees: IntRange,
    @DrawableRes val icon: Int,
) {
    NORTH(IntRange(338, 22), R.drawable.ic_north_black_24dp),
    NORTH_EAST(IntRange(23, 68), R.drawable.ic_north_east_black_24dp),
    EAST(IntRange(69, 113), R.drawable.ic_east_black_24dp),
    SOUTH_EAST(IntRange(114, 158), R.drawable.ic_south_east_black_24dp),
    SOUTH(IntRange(159, 203), R.drawable.ic_south_black_24dp),
    SOUTH_WEST(IntRange(204, 248), R.drawable.ic_south_west_black_24dp),
    WEST(IntRange(249, 293), R.drawable.ic_west_black_24dp),
    NORTH_WEST(IntRange(294, 337), R.drawable.ic_north_west_black_24dp),
    UNKNOWN(IntRange(361, Int.MAX_VALUE), R.drawable.ic_explore_off_black_24dp);

    companion object {
        fun getWindFromWindDegree(windDegree: Double): WindDirection {
            val degree = windDegree.toInt()

            return enumValues<WindDirection>().find { it.degrees.contains(degree) }
                ?: UNKNOWN
        }
    }
}
