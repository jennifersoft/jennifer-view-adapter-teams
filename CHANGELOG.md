# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [2.0.0] - 2025-01-14

### Breaking Changes
- Migrated from MessageCard to AdaptiveCard format
- Required for Power Automate Workflows compatibility
- Minimum Teams version requirements updated

### Added
- AdaptiveCard v1.2 support for future-proof compatibility
- Enhanced error handling with detailed HTTP status codes (400, 404, 429, etc.)
- Better logging for debugging webhook issues
- Support for Power Automate Workflow webhooks
- Monospace font for message body (better readability)
- Improved message formatting with markdown bold text
- Color-coded event levels (FATAL: red, WARNING: yellow, INFO: blue)

### Changed
- Message format from code blocks (```) to markdown bold formatting
- Timeout increased from 5s to 10s for Power Automate compatibility
- Color handling from hex values to predefined AdaptiveCard styles
- Error messages now more descriptive and actionable
- Package version updated to 2.0.0

### Fixed
- Kotlin string interpolation for $schema property
- URL encoding for special characters in transaction links
- Response stream handling for HTTP error cases
- Connection timeout issues with slow webhook endpoints

### Deprecated
- Office 365 Connector support (will be removed Dec 2025)
- MessageCard format (no longer supported by Microsoft)

### Security
- Updated to Kotlin 2.1.20
- Improved error handling to prevent information leakage

## [1.0.0] - 2024

### Added
- Initial release
- MessageCard format support
- Basic webhook integration with O365 Connectors
- Transaction link generation for X-View
- Event notification support (FATAL, WARNING, INFO)
- Configurable webhook URL and JENNIFER URL

---

## Migration Guide (v1.0.0 → v2.0.0)

### For Users
1. **No action required** if you only update the adapter JAR
2. **Recommended**: Migrate to Power Automate Workflows before Dec 2025
3. Update webhook URL by Jan 31, 2025 if using O365 Connectors

### For Developers
- Update dependency to version 2.0.0
- Review logs for any new error messages
- Test with both O365 Connectors and Power Automate Workflows

### Visual Changes
- Event cards now use AdaptiveCard format
- Colors may appear slightly different (system-defined vs custom hex)
- Message body uses monospace font for better readability
- Transaction button remains the same functionality

---

[2.0.0]: https://github.com/jennifersoft/jennifer-view-adapter-teams/compare/v1.0.0...v2.0.0
[1.0.0]: https://github.com/jennifersoft/jennifer-view-adapter-teams/releases/tag/v1.0.0
