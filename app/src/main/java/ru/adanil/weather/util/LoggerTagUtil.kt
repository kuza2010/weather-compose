package ru.adanil.weather.util

class LoggerTagUtil {

    companion object {
        inline fun <reified T> getTag(): String {
            return T::class.java.simpleName
        }
    }
}