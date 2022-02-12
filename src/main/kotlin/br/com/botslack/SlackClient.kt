package br.com.botslack

import br.com.botslack.config.Config
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
data class SlackClient(private val slackRestTemplate: RestTemplate, private val config: Config) {
    fun sendMessage(message: SlackMessage) {
        val headers: HttpHeaders = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val entity: HttpEntity<SlackMessage> = HttpEntity(message, headers)

        slackRestTemplate.exchange(
            config.url,
            HttpMethod.POST,
            entity,
            String::class.java
        )
    }
}