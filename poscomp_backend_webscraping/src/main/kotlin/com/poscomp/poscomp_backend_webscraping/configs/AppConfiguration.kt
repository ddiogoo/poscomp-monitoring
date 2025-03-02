package com.poscomp.poscomp_backend_webscraping.configs

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(HttpServiceConfigData::class)
class AppConfiguration {
    fun httpServiceConfigData(): HttpServiceConfigData {
        return HttpServiceConfigData()
    }
}
