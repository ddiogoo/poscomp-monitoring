package com.poscomp.poscomp_backend_webscraping.services.selenium

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class WebScrapingService {
    private val logger = LoggerFactory.getLogger(javaClass)
    private val url = "https://www.sbc.org.br/poscomp/"
    private val xpath = "//*[@id=\"kubio\"]/body/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/h3"

    private val driver: WebDriver = ChromeDriver().apply {
        manage().timeouts().implicitlyWait(Duration.ofSeconds(15))
        get(url)
    }

    fun fetchWebElement(): String? {
        return try {
            val element: WebElement = driver.findElement(By.xpath(xpath))
            element.text
        } catch (e: Exception) {
            logger.error("Error during web scraping: ${e.message}")
            null
        }.toString()
    }

    fun closeDriver() {
        driver.quit()
    }
}
