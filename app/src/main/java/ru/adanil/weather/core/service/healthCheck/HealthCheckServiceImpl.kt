package ru.adanil.weather.core.service.healthCheck

import android.util.Log
import retrofit2.HttpException
import ru.adanil.weather.core.gateway.HealthCheckGateway
import ru.adanil.weather.util.LoggerTagUtil
import javax.inject.Inject

class HealthCheckServiceImpl @Inject constructor(
    private val healthCheckGateway: HealthCheckGateway
) : HealthCheckService {

    private val TAG = LoggerTagUtil.getTag<HealthCheckService>()

    override fun isApiAvailable(): Boolean {
        return try {
            val result = healthCheckGateway.healthCheck()
                .blockingGet()

            // 404 means api is available
            return when (result) {
                is HttpException -> {
                    result.code() == 404
                }
                else -> {
                    false
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Unexpected error during api-health check call: ${ex.message}", ex)
            false
        }
    }

}