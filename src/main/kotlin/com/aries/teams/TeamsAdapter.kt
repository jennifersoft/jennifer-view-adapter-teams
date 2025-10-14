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
 * Teams Event Adapter for JENNIFER
 * Version 2.0 - AdaptiveCard format
 */
class TeamsAdapter : EventHandler {
    private val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

    override fun on(eventData: Array<EventData>) {
        val teamsProperties = ConfUtil.getTeamsProperties()

        for (event in eventData) {
            val message = getBody(event)
            val pretext = getPreText(event)

            val teamsMessage = TeamsData(teamsProperties, message, pretext, event)
            val result = TeamsClient(teamsMessage).push().trim()
            
            if (result.isEmpty() || result != "1") {
                LogUtil.error("Failed to push message to Teams. Check logs for details.")
            }
        }
    }

    /**
     * AdaptiveCard의 TextBlock은 markdown을 지원하지만
     * 코드 블록(```)은 지원하지 않으므로 일반 텍스트로 변경
     */
    private fun getBody(event: EventData): String {
        val messageBody = StringBuilder()
        
        // AdaptiveCard에서 보기 좋게 줄바꿈과 구분선 사용
        messageBody.append("**Domain ID:** ${event.domainId}\n\n")
        messageBody.append("**Domain Name:** ${event.domainName}\n\n")
        messageBody.append("**Instance Name:** ${event.instanceName}\n\n")
        messageBody.append("**Transaction ID:** ${event.txid}\n\n")
        messageBody.append("**Service Name:** ${event.serviceName}\n\n")
        messageBody.append("**Error Type:** ${event.errorType}\n\n")
        messageBody.append("**Error Level:** ${event.eventLevel}\n\n")
        messageBody.append("**Error Time:** ${sdf.format(Date(event.time))}")
        
        return messageBody.toString()
    }

    private fun getPreText(event: EventData): String {
        return "⚠️ JENNIFER Event Alert: ${event.errorType}"
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
