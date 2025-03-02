package com.poscomp.poscomp_backend_webscraping.services.cloud

import com.poscomp.poscomp_backend_webscraping.configs.HttpServiceConfigData
import com.poscomp.poscomp_backend_webscraping.services.interfaces.IHttpService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(name = ["http-service.which-lambda-service"], havingValue = "aws-lambda")
class AwsLambdaService(private val httpService: HttpServiceConfigData) : IHttpService {
    private val logger = LoggerFactory.getLogger(javaClass)
    private val client = OkHttpClient()
    private val mediaType = "application/json".toMediaType()

    override fun request(body: String): String {
        val request = Request.Builder()
            .url(httpService.url)
            .post(body.toRequestBody(mediaType))
            .addHeader("Content-Type", "application/json")
            .build()

        return try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    logger.error("Request to AWS Lambda failed with status: {}", response.code)
                    throw Exception("Unexpected HTTP status: ${response.code}")
                }
                response.body?.string() ?: throw Exception("Empty response body")
            }
        } catch (e: Exception) {
            logger.error("Error while calling AWS Lambda: {}", e.message, e)
            throw e
        }
    }

    override fun serviceName(): String = "AWS Lambda"
}
