package br.com.botslack.util

import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.impl.client.HttpClients
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import java.util.concurrent.TimeUnit

class RestTemplateBuilder {

    fun restTemplate(
        maxConn: Int,
        maxPerRoute: Int,
        connTimeout: Int,
        connReadTimeout: Int,
        connTTL: Int
    ): RestTemplate {
        return RestTemplate(httpRequestFactory(maxConn, maxPerRoute, connTimeout, connReadTimeout, connTTL))
    }

    fun connectionManager(maxConn: Int, maxPerRoute: Int, connTTL: Int): PoolingHttpClientConnectionManager {
        val poolingConnManager: PoolingHttpClientConnectionManager =
            PoolingHttpClientConnectionManager(connTTL.toLong(), TimeUnit.MILLISECONDS)
        poolingConnManager.maxTotal = maxConn
        poolingConnManager.defaultMaxPerRoute = maxPerRoute
        return poolingConnManager
    }

    fun httpClientBuilder(maxConn: Int, maxPerRoute: Int, connTTL: Int): HttpClientBuilder {
        return HttpClients.custom()
            .setConnectionManager(
                connectionManager(
                    maxConn,
                    maxPerRoute,
                    connTTL
                )
            )
            .setConnectionTimeToLive(connTTL.toLong(), TimeUnit.MILLISECONDS)
    }

    fun httpClient(maxConn: Int, maxPerRoute: Int, connTTL: Int): CloseableHttpClient {
        return httpClientBuilder(
            maxConn,
            maxPerRoute,
            connTTL
        )
            .disableAutomaticRetries()
            .evictExpiredConnections()
            .build()
    }

    fun httpRequestFactory(
        maxConn: Int,
        maxPerRouut: Int,
        connTimeout: Int,
        readTimeout: Int,
        connTTL: Int
    ): HttpComponentsClientHttpRequestFactory {
        val requestFactory: HttpComponentsClientHttpRequestFactory = HttpComponentsClientHttpRequestFactory()

        requestFactory.setHttpClient(httpClient(maxConn, maxPerRouut, connTTL))
        requestFactory.setConnectTimeout(connTimeout)
        requestFactory.setReadTimeout(readTimeout)
        return requestFactory
    }
}