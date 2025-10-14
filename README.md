# MS Teams Event Adapter for JENNIFER

⚠️ **IMPORTANT NOTICE - Office 365 Connectors Deprecation**

Microsoft is deprecating Office 365 Connectors within Microsoft Teams:
- **January 31, 2025**: Webhook URL update required (mandatory)
- **December 2025**: Complete deprecation of O365 Connectors

**This adapter (v2.0+) uses AdaptiveCard format** which is compatible with both:
- Legacy O365 Connectors (until Dec 2025)
- Power Automate Workflows (recommended going forward)

## Overview

This adapter sends JENNIFER EVENT notifications to Microsoft Teams channels using AdaptiveCard format.

## Migration Timeline

### Phase 1: URL Update (by Jan 31, 2025)
If you're using existing O365 Connectors:
1. Update webhook URLs in Teams
2. No code changes needed

### Phase 2: Workflow Migration (by Dec 2025) - Recommended
Migrate to Power Automate Workflows:
1. In Teams, go to **Workflows**
2. Select **"Post to a channel when a webhook request is received"**
3. Use the generated workflow webhook URL
4. Benefits: Better security, more features, Microsoft's recommended approach

## Getting Started

### 1. Adapter Installation

1. In JENNIFER Dashboard, navigate to **Settings** > **SMTP and Adapter (+DB Plan)**
2. Click the **Adapter** tab
3. Click **[+Add]** button
4. Select **Teams Adapter** from the dropdown
5. Click **Save**

<img width="1950" alt="Adapter Installation" src="https://github.com/user-attachments/assets/f912778b-4eae-4847-bf23-f3a9d5d4a285" />

### 2. Configure Webhook URL

#### Option A: Power Automate Workflow (Recommended)

1. In Microsoft Teams, go to the channel where you want notifications
2. Click **Workflows** (lightning bolt icon)
3. Search for **"Post to a channel when a webhook request is received"**
4. Create the workflow and copy the generated webhook URL
5. Paste the URL in JENNIFER adapter configuration

#### Option B: Legacy O365 Connector (until Dec 2025)

1. In Teams channel, click **⋯** > **Connectors**
2. Search for **"Incoming Webhook"**
3. Configure and copy the webhook URL
4. **Important**: Update this URL by January 31, 2025

### 3. Adapter Configuration

<img width="1955" alt="Adapter Configuration" src="https://github.com/user-attachments/assets/3a8d57e2-9fb1-4fce-8fcf-03368f3765e5" />

| Key | Required | Description | Default |
|-----|----------|-------------|---------|
| `webhook_url` | **YES** | Teams Webhook URL (O365 Connector or Workflow) | None |
| `jennifer_url` | NO | JENNIFER View Server URL for transaction links | None |

**Example Configuration:**
```
webhook_url: https://prod-XX.eastus.logic.azure.com:443/workflows/...
jennifer_url: https://jennifer.example.com
```

## Message Format

Version 2.0+ uses **AdaptiveCard** format with the following features:

- 🎨 **Color-coded alerts**: FATAL (red), WARNING (yellow), INFO (blue)
- 📊 **Structured information**: All event details clearly displayed
- 🔗 **Transaction link**: Direct link to X-View analysis (if configured)
- 📱 **Mobile-friendly**: Responsive design for all devices

### Sample Message

<img width="961" alt="Sample Teams Message" src="https://github.com/user-attachments/assets/c0fff6ff-049d-4f1d-a42c-43a1f517fa5b" />

## Troubleshooting

### Error: "Failed to push message to Teams"

**Check the following:**

1. **Webhook URL is correct and active**
   - For O365 Connectors: Verify it hasn't been deleted
   - For Workflows: Check workflow is enabled

2. **Network connectivity**
   - JENNIFER server can reach Teams endpoints
   - No firewall blocking outbound HTTPS

3. **Webhook URL expired**
   - O365 Connectors: Regenerate webhook
   - Workflows: Check workflow status in Power Automate

### Error: "Bad Request (400)"

This indicates invalid AdaptiveCard format:
- Ensure you're using adapter version 2.0+
- Check JENNIFER logs for detailed error messages
- Verify webhook URL is for Teams (not Slack/other services)

### Error: "Rate limit exceeded (429)"

- Teams/Power Automate has rate limits
- Space out your events or batch notifications
- Consider using event filters in JENNIFER

## Building

```bash
# Build the project
mvn clean package

# Output will be in dist/teams-adapter-2.0.0.jar
```

## Version History

### v2.0.0 (2025)
- ✨ Migrated to AdaptiveCard format
- 🔧 Improved error handling and logging
- 📝 Enhanced message formatting
- ⚡ Better timeout handling for Power Automate
- 📚 Updated documentation with deprecation info

### v1.0.0 (Previous)
- Initial release with MessageCard format
- ⚠️ **Deprecated**: MessageCard no longer supported

## Additional Resources

- [Microsoft's O365 Connector Deprecation Announcement](https://devblogs.microsoft.com/microsoft365dev/retirement-of-office-365-connectors-within-microsoft-teams/)
- [Power Automate Workflows in Teams](https://support.microsoft.com/en-us/office/create-incoming-webhooks-with-workflows-for-microsoft-teams-8ae491c7-0394-4861-ba59-055e33f75498)
- [AdaptiveCards Documentation](https://adaptivecards.io/)
- [JENNIFER Documentation](https://www.jennifersoft.com)

## Support

For issues or questions:
- GitHub Issues: [jennifer-view-adapter-teams/issues](https://github.com/jennifersoft/jennifer-view-adapter-teams/issues)
- JENNIFER Support: support@jennifersoft.com

## License

This project follows JENNIFER's licensing terms.
