package com.poscomp.poscomp_backend_webscraping.configs

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "http-service")
data class HttpServiceConfigData(
    var url: String = ""
)
