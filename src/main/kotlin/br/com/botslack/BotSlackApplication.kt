package br.com.botslack

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BotSlackApplication

fun main(args: Array<String>) {
	runApplication<BotSlackApplication>(*args)
}
