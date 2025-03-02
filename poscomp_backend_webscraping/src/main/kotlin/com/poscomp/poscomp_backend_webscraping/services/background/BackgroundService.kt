package com.poscomp.poscomp_backend_webscraping.services.background

import com.poscomp.poscomp_backend_webscraping.services.selenium.WebScrapingService
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class BackgroundService(private val webScrapingService: WebScrapingService) {
    private val logger = LoggerFactory.getLogger(javaClass)
    @Async
    fun startBackgroundService() {
        while(true) {
            try {
                logger.info(webScrapingService.fetchWebElement())
            } catch (e: Exception) {
                logger.error("Error on running background task ${e.message}")
                webScrapingService.closeDriver()
            }
            Thread.sleep(5000)
        }
    }
}
