package br.com.botslack.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class Config(
    @Value("\${slack.url}") var url: String,
    @Value("\${slack.max-conn}") var maxConn: Int,
    @Value("\${slack.max-per-route}") var maxPerRoute: Int,
    @Value("\${slack.read-timeout}") var readTimeoutMilliseconds: Int,
    @Value("\${slack.conn-timeout}") var connTimeoutMilliseconds: Int,
    @Value("\${slack.conn-ttl}") var connectionTTLMilliseconds: Int
)