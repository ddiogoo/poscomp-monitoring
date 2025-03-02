package com.poscomp.poscomp_backend_webscraping

import com.poscomp.poscomp_backend_webscraping.services.background.BackgroundService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
@ComponentScan("com.poscomp.poscomp_backend_webscraping")
class PoscompBackendWebscrapingApplication(private val backgroundService: BackgroundService) : CommandLineRunner {
	override fun run(vararg args: String?) {
		backgroundService.startBackgroundService()
	}
}

fun main(args: Array<String>) {
	runApplication<PoscompBackendWebscrapingApplication>(*args)
}
