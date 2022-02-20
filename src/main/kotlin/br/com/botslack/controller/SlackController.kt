package br.com.botslack.controller

import br.com.botslack.dto.MessageDTO
import br.com.botslack.service.SlackNotificationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/message")
class SlackController(
    private val slackNotificationService: SlackNotificationService
) {
    @PostMapping
    fun sendMessage(@RequestBody messageDTO: MessageDTO): ResponseEntity<String> {
        slackNotificationService.notifySlack(messageDTO.message)
        return ResponseEntity.ok("enviado ")
    }
}