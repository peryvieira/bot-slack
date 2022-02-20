package br.com.botslack.service

import br.com.botslack.SlackAttachment
import br.com.botslack.SlackClient
import br.com.botslack.SlackMessage
import org.springframework.stereotype.Service


@Service
open class SlackNotificationService(private val slackClient: SlackClient) {

    fun notifySlack(message: String = "") {
        val slackMessage = SlackMessage()
        val slackAttachment = SlackAttachment()
        val colorNotification = if (message.isEmpty()) "#36a64f" else "#FF0000"

        slackAttachment.apply {
            color = colorNotification
            text = String.format(
                "Menssagem : %s",
                message
            )
        }

        slackMessage.apply {
            attachments = mutableListOf(slackAttachment)
        }

        try {
            slackClient.sendMessage(slackMessage)
        } catch (exception: Exception) {
            println(exception)
        }
    }
}