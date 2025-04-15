package com.aries.teams.util

import com.aries.extension.util.PropertyUtil
import com.aries.teams.entity.TeamsProp

/**
 * Load adapter configuration
 */
object ConfUtil {
    private val teamsProperties = TeamsProp()


    /**
     * The adapter ID
     */
    private const val ADAPTER_ID = "teams"

    /**
     * Get a configuration value using the provided key
     * @param key configuration key. Set this key value in the adapter configuration menu in JENNIFER client.
     * @param defaultValue Optional default configuration value
     * @return String configuration value
     */
    fun getValue(key: String?, defaultValue: String?): String {
        return PropertyUtil.getValue(ADAPTER_ID, key, defaultValue)
    }

    /**
     * Get the slack properties
     * @return SlackProp slack properties
     */
    fun getTeamsProperties(): TeamsProp {
        teamsProperties.webHookUrl = getValue("webhook_url", null)
        teamsProperties.shareUrl = getValue("jennifer_url", null)
        return teamsProperties
    }

    fun _getTeamsProperties(): TeamsProp {
        teamsProperties.webHookUrl = getValue("webhook_url", "https://jennifer5.webhook.office.com/webhookb2/0b01b64b-c941-4988-a360-c242f341fa60@fb9a94cd-eba0-41db-b029-9b6c35225ea5/IncomingWebhook/955bf3571a0e42cbb07e7bdfd3a56016/491388af-67ce-443e-a15c-b8072411abb0/V27H-M8LwVaj13mKiKW0QRTPRIPGAaJqZF8cVJ7MWL2ng1")
        teamsProperties.shareUrl = getValue("jennifer_url", "http://127.0.0.1:7901")
        return teamsProperties
    }
}
