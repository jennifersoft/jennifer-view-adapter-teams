package com.aries.teams.entity

import com.aries.extension.data.EventData
import org.json.JSONArray
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

/**
 * Slack Message class
 */
class TeamsData(
    val prop: TeamsProp,
    private val message: String,
    private val pretext: String,
    private val event: EventData
) : JSONObject() {
    init {
        this.addDefaultInfo()
        this.addAttachments()
    }

    private fun addDefaultInfo() {
        this.put("@context", "https://schema.org/extensions")
        this.put("@type", "MessageCard")

        if (event.eventLevel == "FATAL") this.put("themeColor", "ff384d")
        else if (event.eventLevel == "WARNING") this.put("themeColor", "ffdd00")
        else this.put("themeColor", "497eff")

        this.put("title", this.pretext)
        this.put("text", this.message)
    }

    private fun addAttachments() {
        if (prop.shareUrl != null && event.txid != -1L && event.txid != 0L) {
            val popupUrl = "/popup/xviewAnalysisV2?domainId=" + event.domainId +
                    "&transactionId=" + event.txid + "&searchTime=" + event.time
            val link = prop.shareUrl + popupUrl + "&redirect=" + encodeURIComponent(popupUrl)

            val targets = JSONArray()
            val target = JSONObject()
            target.put("os", "default")
            target.put("uri", link)
            targets.put(target)

            val attachments = JSONArray()
            val obj = JSONObject()
            obj.put("@type", "OpenUri")
            obj.put("name", "View Transaction (JENNIFER5 Pop-Up)")
            obj.put("targets", targets)
            attachments.put(obj);

            this.put("potentialAction", attachments)
        }
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
            } // This exception should never occur.

            catch (e: UnsupportedEncodingException) {
                s
            }

            return result!!
        }
    }
}
