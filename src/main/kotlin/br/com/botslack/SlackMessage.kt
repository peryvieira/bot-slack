package br.com.botslack

data class SlackMessage(
    var channel: String? = null,
    var text: String? = null,
    var attachments: List<SlackAttachment> = emptyList()
)
