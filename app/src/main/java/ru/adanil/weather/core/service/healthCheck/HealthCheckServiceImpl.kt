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

    override suspend fun isApiAvailable(): Boolean {
        return try {
            val response = healthCheckGateway.healthCheck()
            response.isSuccessful || response.code() == 404
        } catch (ex: Exception) {
            // 404 means api is available
            val status = when (ex) {
                is HttpException -> ex.code() == 404
                else -> {
                    Log.e(TAG, "Unexpected error during api-health check call: ${ex.message}", ex)
                    false
                }
            }
            status
        }
    }

}