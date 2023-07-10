package com.example.service

import io.ktor.server.config.*
import org.apache.commons.mail.EmailException
import org.apache.commons.mail.SimpleEmail

class EmailService(config: HoconApplicationConfig) {
    private val hostname = config.property("ktor.email.hostname").getString()
    private val port = config.property("ktor.email.port").getString().toInt()
    private val username = config.property("ktor.email.username").getString()
    private val password = config.property("ktor.email.password").getString()

    fun sendEmail(toEmail: String, subjectString: String, msgString: String): Boolean {
        return try {
            val email = SimpleEmail().apply {
                hostName = hostname
                setSmtpPort(port)
                setAuthentication(username, password)
                setFrom(username)
                subject = subjectString
                setMsg(msgString)
                addTo(toEmail)
                isSSLOnConnect = true
            }
            email.send()
            true
        } catch (e: EmailException) {

            false
        }
    }
}


