package br.com.botslack

data class SlackAttachment(
    var text: String? = null,
    var color: String? = null,
    private val pretext: String? = null,
    private val title: String? = null
)