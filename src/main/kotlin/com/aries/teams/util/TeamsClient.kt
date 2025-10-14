package com.aries.teams.util

import com.aries.extension.util.LogUtil
import com.aries.teams.entity.TeamsData
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URI
import java.nio.charset.StandardCharsets

/**
 * Teams Client for pushing message to Teams
 * Supports both O365 Connectors and Power Automate Workflows
 */
class TeamsClient(
    private val teamsData: TeamsData
) {
    companion object {
        // Power Automate Workflow는 더 느릴 수 있음
        private const val CONNECT_TIMEOUT = 10000  // 10초
        private const val READ_TIMEOUT = 10000     // 10초
    }

    /**
     * Push message to Teams using URLConnection
     * @return "1" if successful, empty string if failed
     */
    fun push(): String {
        var connection: HttpURLConnection? = null
        try {
            // URI를 사용하여 URL 생성 (deprecated 경고 해결)
            val url = URI(teamsData.prop.webHookUrl).toURL()
            connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.connectTimeout = CONNECT_TIMEOUT
            connection.readTimeout = READ_TIMEOUT
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8")
            connection.setRequestProperty("Accept", "application/json")
            connection.useCaches = false
            connection.doOutput = true

            val jsonMessage = teamsData.toString()
            
            // 디버깅을 위한 로깅 (개발 환경에서만 활성화)
            // LogUtil.debug("Sending to Teams: $jsonMessage")

            DataOutputStream(connection.outputStream).use { out ->
                out.write(jsonMessage.toByteArray(StandardCharsets.UTF_8))
                out.flush()
            }

            val responseCode = connection.responseCode
            
            // 응답 코드에 따른 처리
            when (responseCode) {
                HttpURLConnection.HTTP_OK, 
                HttpURLConnection.HTTP_ACCEPTED -> {
                    // 성공적으로 전송됨
                    val response = readResponse(connection.inputStream)
                    LogUtil.info("Successfully sent message to Teams. Response: $response")
                    return "1"
                }
                HttpURLConnection.HTTP_BAD_REQUEST -> {
                    val errorResponse = readResponse(connection.errorStream)
                    LogUtil.error("Bad Request (400): Invalid AdaptiveCard format or webhook URL. Response: $errorResponse")
                    return ""
                }
                HttpURLConnection.HTTP_NOT_FOUND -> {
                    LogUtil.error("Not Found (404): Webhook URL not found or expired. Please regenerate webhook URL.")
                    return ""
                }
                429 -> {  // Too Many Requests
                    LogUtil.error("Rate limit exceeded (429): Too many requests to Teams webhook.")
                    return ""
                }
                HttpURLConnection.HTTP_UNAUTHORIZED, 
                HttpURLConnection.HTTP_FORBIDDEN -> {
                    LogUtil.error("Unauthorized/Forbidden ($responseCode): Check webhook URL permissions.")
                    return ""
                }
                else -> {
                    val errorResponse = readResponse(connection.errorStream)
                    LogUtil.error("Unexpected response code: $responseCode. Response: $errorResponse")
                    return ""
                }
            }
        } catch (ex: Exception) {
            // Exception 스택트레이스를 문자열로 변환
            val stackTrace = ex.stackTraceToString()
            LogUtil.error("Error while pushing message to Teams: ${ex.message}\nStack trace: $stackTrace")
            return ""
        } finally {
            connection?.disconnect()
        }
    }

    /**
     * 응답 스트림을 읽어서 문자열로 반환
     */
    private fun readResponse(inputStream: java.io.InputStream?): String {
        if (inputStream == null) return ""
        
        return try {
            BufferedReader(InputStreamReader(inputStream)).use { reader ->
                reader.readText()
            }
        } catch (e: Exception) {
            ""
        }
    }
}
