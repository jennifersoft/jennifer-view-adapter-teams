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
}
