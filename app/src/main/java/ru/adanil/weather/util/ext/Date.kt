package ru.adanil.weather.util.ext

import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.TimeZone

sealed class TimeFormatter(val pattern: DateTimeFormatter) {
    object HMTimeFormatter : TimeFormatter(DateTimeFormatter.ofPattern("HH:mm"))
    object DefaultTimeFormatter : TimeFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
}

fun Timestamp.format(formatter: TimeFormatter): String {
    return LocalDateTime
        .ofInstant(toInstant(), TimeZone.getDefault().toZoneId())
        .format(formatter.pattern)
}

fun LocalDateTime.format(formatter: TimeFormatter): String {
    return format(formatter.pattern)
}
