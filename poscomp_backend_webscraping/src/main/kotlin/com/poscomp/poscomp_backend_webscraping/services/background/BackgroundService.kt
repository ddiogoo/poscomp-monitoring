package com.poscomp.poscomp_backend_webscraping.services.background

import com.google.gson.Gson
import com.poscomp.poscomp_backend_webscraping.models.HttpModelRequest
import com.poscomp.poscomp_backend_webscraping.services.interfaces.IHttpService
import com.poscomp.poscomp_backend_webscraping.services.selenium.WebScrapingService
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class BackgroundService(
    private val webScrapingService: WebScrapingService, private val iHttpService: IHttpService
) {
    private val gsonService = Gson()
    private val logger = LoggerFactory.getLogger(javaClass)
    @Async
    fun startBackgroundService() {
        while(true) {
            try {
                val request = webScrapingService.fetchWebElement()?.let { HttpModelRequest(message = it) }
                logger.info("Trying to sending a request to ${iHttpService.serviceName()}...")
                val response = iHttpService.request(gsonService.toJson(request))
                logger.info(response)
            } catch (e: Exception) {
                logger.error("Error on running background task ${e.message}")
                webScrapingService.closeDriver()
            }
            Thread.sleep(Duration.ofMinutes(30).toMillis())
        }
    }
}
