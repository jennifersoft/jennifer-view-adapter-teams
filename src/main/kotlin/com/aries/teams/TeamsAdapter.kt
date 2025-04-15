package com.aries.teams

import com.aries.extension.data.EventData
import com.aries.extension.handler.EventHandler
import com.aries.extension.util.LogUtil
import com.aries.teams.entity.TeamsData
import com.aries.teams.util.ConfUtil
import com.aries.teams.util.TeamsClient
import java.text.SimpleDateFormat
import java.util.*

/**
 * The main logic for the extension
 *
 */
class TeamsAdapter : EventHandler {
    /**
     * Format the date and time
     */
    private val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

    override fun on(eventData: Array<EventData>) {
//		val teamsProperties = ConfUtil._getTeamsProperties();
        val teamsProperties = ConfUtil.getTeamsProperties()

        for (event in eventData) {
            val message = getBody(event)
            val pretext = getPreText(event)

            val teamsMessage = TeamsData(teamsProperties, message, pretext, event)
            println(teamsMessage)
            val result = TeamsClient(teamsMessage).push().trim { it <= ' ' }
            if (!result.trim { it <= ' ' }
                    .equals("ok", ignoreCase = true)) LogUtil.error("Failed to push message to Teams")
        }
    }

    private fun getBody(event: EventData): String {
        val messageBody = StringBuilder()
        messageBody.append(String.format("```Domain ID: %d%n", event.domainId))
        messageBody.append(String.format("Domain Name: %s%n", event.domainName))
        messageBody.append(String.format("Instance Name: %s%n", event.instanceName))
        messageBody.append(String.format("Transaction ID: %d%n", event.txid))
        messageBody.append(String.format("Service Name: %s%n", event.serviceName))
        messageBody.append(String.format("Error Type: %s%n", event.errorType))
        messageBody.append(String.format("Error Level: %s%n", event.eventLevel))
        messageBody.append(String.format("Error Time: %s%n", sdf.format(Date(event.time))))
        return messageBody.toString()
    }

    private fun getPreText(event: EventData): String {
        val pretext = StringBuilder()
        pretext.append(String.format("The following event [%s] was caught by JENNIFER. %n", event.errorType))
        pretext.append("Here are some additional details\n")
        return pretext.toString()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val event = EventData(
                1004.toShort(),
                ArrayList(),
                "제니퍼",
                System.currentTimeMillis(),
                1000,
                "Groupware",
                "",
                "SERVICE_EXCEPTION",
                "",
                "FATAL",
                "",
                -1.0,
                "SYSTEM",
                "",
                "/service.jsp",
                -123123123,
                "",
                null
            )
            TeamsAdapter().on(arrayOf(event))
        }
    }
}
