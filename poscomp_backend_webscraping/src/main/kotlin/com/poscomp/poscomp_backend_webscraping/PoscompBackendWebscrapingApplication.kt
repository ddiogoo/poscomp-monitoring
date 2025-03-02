package com.poscomp.poscomp_backend_webscraping

import com.poscomp.poscomp_backend_webscraping.services.IHttpService
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.poscomp.poscomp_backend_webscraping")
class PoscompBackendWebscrapingApplication(
	private val httpService: IHttpService
) : CommandLineRunner {
	private val logger = LoggerFactory.getLogger(javaClass)

	override fun run(vararg args: String?) {
		httpService.request("")
	}
}

fun main(args: Array<String>) {
	runApplication<PoscompBackendWebscrapingApplication>(*args)
}
