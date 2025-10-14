package com.aries.teams.entity

import com.aries.extension.data.EventData
import org.json.JSONArray
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

/**
 * Teams Message class (AdaptiveCard format)
 * 
 * IMPORTANT: Office 365 Connectors deprecated (Dec 2025)
 * This version uses AdaptiveCard format required for Power Automate Workflows
 */
class TeamsData(
    val prop: TeamsProp,
    private val message: String,
    private val pretext: String,
    private val event: EventData
) : JSONObject() {

    init {
        // 루트 오브젝트 생성 (AdaptiveCard 형식)
        this.put("type", "message")
        
        val attachmentsArray = JSONArray()
        val attachment = JSONObject()
        attachment.put("contentType", "application/vnd.microsoft.card.adaptive")
        
        val content = JSONObject()
        content.put("type", "AdaptiveCard")
        // Kotlin string interpolation으로 $ 이스케이프
        content.put("${"$"}schema", "http://adaptivecards.io/schemas/adaptive-card.json")
        content.put("version", "1.2")
        
        // Teams에서 전체 너비로 표시
        content.put("msteams", JSONObject().put("width", "Full"))

        // --- 카드 본문 (Body) 생성 ---
        val bodyArray = JSONArray()
        
        // 1. 헤더 컨테이너 (제목)
        val headerContainer = JSONObject()
        headerContainer.put("type", "Container")

        // 이벤트 레벨에 따른 스타일 지정
        val style = when (event.eventLevel) {
            "FATAL" -> "attention"    // 빨간색
            "WARNING" -> "warning"    // 노란색
            else -> "emphasis"        // 회색
        }
        headerContainer.put("style", style)

        val headerItems = JSONArray()
        val titleBlock = JSONObject()
        titleBlock.put("type", "TextBlock")
        titleBlock.put("text", this.pretext)
        titleBlock.put("size", "Large")
        titleBlock.put("weight", "Bolder")
        titleBlock.put("wrap", true)
        headerItems.put(titleBlock)
        headerContainer.put("items", headerItems)
        bodyArray.put(headerContainer)

        // 2. 본문 컨테이너 (메시지)
        val bodyContainer = JSONObject()
        bodyContainer.put("type", "Container")
        val bodyItems = JSONArray()
        val messageBlock = JSONObject()
        messageBlock.put("type", "TextBlock")
        messageBlock.put("text", this.message)
        messageBlock.put("wrap", true)
        messageBlock.put("isSubtle", false)
        // monospace font를 위한 스타일
        messageBlock.put("fontType", "Monospace")
        bodyItems.put(messageBlock)
        bodyContainer.put("items", bodyItems)
        bodyArray.put(bodyContainer)

        content.put("body", bodyArray)
        
        // --- 액션 (버튼) 생성 ---
        if (prop.shareUrl != null && event.txid != -1L && event.txid != 0L) {
            val actionsArray = JSONArray()
            
            val popupUrl = "/popup/xviewAnalysisV2?domainId=" + event.domainId +
                    "&transactionId=" + event.txid + "&searchTime=" + event.time
            val link = prop.shareUrl + popupUrl + "&redirect=" + encodeURIComponent(popupUrl)

            val action = JSONObject()
            action.put("type", "Action.OpenUrl")
            action.put("title", "View Transaction (JENNIFER5 Pop-Up)")
            action.put("url", link)
            actionsArray.put(action)
            
            content.put("actions", actionsArray)
        }

        // 완성된 content를 attachment에 삽입
        attachment.put("content", content)
        attachmentsArray.put(attachment)
        this.put("attachments", attachmentsArray)
    }

    companion object {
        fun encodeURIComponent(s: String): String {
            var result: String? = null

            result = try {
                URLEncoder.encode(s, "UTF-8")
                    .replace("\\+".toRegex(), "%20")
                    .replace("\\%21".toRegex(), "!")
                    .replace("\\%27".toRegex(), "'")
                    .replace("\\%28".toRegex(), "(")
                    .replace("\\%29".toRegex(), ")")
                    .replace("\\%7E".toRegex(), "~")
            } catch (e: UnsupportedEncodingException) {
                s
            }

            return result!!
        }
    }
}
