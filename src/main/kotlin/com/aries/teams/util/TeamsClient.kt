package com.aries.teams.util

import com.aries.extension.util.LogUtil
import com.aries.teams.entity.TeamsData
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

/**
 * Slack Client for pushing message to slack
 *
 */
class TeamsClient
/**
 * Default constructor
 * @param message SlackMessage object
 */(
    /**
     * SlackMessage instance
     */
    private val teamsData: TeamsData
) {
    /**
     * Push message to slack using simple URLConnection
     * @return Return either "ok" if message was sent, or null if message was not sent or an exception occured.
     */
    fun push(): String {
        var connection: HttpURLConnection? = null
        try {
            val url = URL(teamsData.prop.webHookUrl)
            connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.connectTimeout = 5000
            connection.readTimeout = 5000
            connection.setRequestProperty("Content-Type", "application/json; utf-8")
            connection.setRequestProperty("Accept", "application/json")
            connection.useCaches = false
            connection.doOutput = true

            // JSONObject를 사용하여 Teams 메시지 생성
            val jsonMessage = teamsData.toString()

            // 출력 스트림에 JSON 쓰기
            DataOutputStream(connection.outputStream).use { out ->
                out.write(jsonMessage.toByteArray(StandardCharsets.UTF_8))
                out.flush()
            }

            val `in` = connection.inputStream
            val reader = BufferedReader(InputStreamReader(`in`))
            var line: String? = null
            val response = StringBuilder()
            while (reader.readLine()?.also { line = it } != null) {  // 안전 호출 연산자 ?. 추가
                response.append(line + "\n")
            }

            reader.close()
            return response.toString()
        } catch (ex: Exception) {
            LogUtil.error("Error while pushing message. Reason : $ex")
            return ""
        } finally {
            connection?.disconnect()
        }
    }
}
