package com.poscomp.poscomp_backend_webscraping.services.interfaces

interface IApiHttpService {
    fun serviceName(): String
    fun request(body: String): String
}
