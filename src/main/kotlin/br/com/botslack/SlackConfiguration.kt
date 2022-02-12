package br.com.botslack

import br.com.botslack.config.Config
import br.com.botslack.util.RestTemplateBuilder

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
open class SlackConfiguration {
    @Bean
    open fun slackRestTemplate(configBusinessSlack: Config) : RestTemplate {
        val restTemplateBuilder = RestTemplateBuilder()
        return restTemplateBuilder.restTemplate(
                configBusinessSlack.maxConn,
                configBusinessSlack.maxPerRoute,
                configBusinessSlack.connTimeoutMilliseconds,
                configBusinessSlack.readTimeoutMilliseconds,
                configBusinessSlack.connectionTTLMilliseconds)
    }
}