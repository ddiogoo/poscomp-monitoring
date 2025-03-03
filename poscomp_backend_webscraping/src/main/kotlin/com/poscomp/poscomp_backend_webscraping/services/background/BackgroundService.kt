package com.poscomp.poscomp_backend_webscraping.services.background

import com.google.gson.Gson
import com.poscomp.poscomp_backend_webscraping.models.AwsLambdaModelRequest
import com.poscomp.poscomp_backend_webscraping.services.interfaces.IApiHttpService
import com.poscomp.poscomp_backend_webscraping.services.selenium.WebScrapingService
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class BackgroundService(
    private val webScrapingService: WebScrapingService, private val iApiHttpService: IApiHttpService
) {
    private val gsonService = Gson()
    private val logger = LoggerFactory.getLogger(javaClass)
    @Async
    fun startBackgroundService() {
        while(true) {
            try {
                val elementText = webScrapingService.fetchWebElement()
                val request = elementText?.let { AwsLambdaModelRequest(message = it) }
                logger.info("Trying to sending '$elementText' to ${iApiHttpService.serviceName()}...")
                val response = iApiHttpService.request(gsonService.toJson(request))
                logger.info(response)
            } catch (e: Exception) {
                logger.error("Error on running background task ${e.message}")
                webScrapingService.closeDriver()
            }
            Thread.sleep(Duration.ofMinutes(30).toMillis())
        }
    }
}
