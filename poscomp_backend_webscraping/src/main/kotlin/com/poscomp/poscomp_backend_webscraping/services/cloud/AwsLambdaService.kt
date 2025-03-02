package com.poscomp.poscomp_backend_webscraping.services.cloud

import com.poscomp.poscomp_backend_webscraping.configs.HttpServiceConfigData
import com.poscomp.poscomp_backend_webscraping.services.interfaces.IHttpService
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(name = ["http-service.which-lambda-service"], havingValue = "aws-lambda")
class AwsLambdaService(private val httpService: HttpServiceConfigData) : IHttpService {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun request(body: String) {
        logger.info(httpService.url)
    }
}
