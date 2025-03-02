package com.poscomp.poscomp_backend_webscraping

import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.springframework.boot.test.context.SpringBootTest
import java.time.Duration

@SpringBootTest
class PoscompBackendWebscrapingApplicationTests {

	@Test
	fun launchBrowser() {
		val driver: WebDriver = ChromeDriver()
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15))
		driver.get("https://www.sbc.org.br/poscomp/")
		val element = driver.findElement(By.xpath("//*[@id=\"kubio\"]/body/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/h3"))
		println(element.text)
	}
}
