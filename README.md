# Overview
This adapter will send EVENT notification to a teams board.

## Getting started

### 1- Adapter Installation
The first step is to register the adapter: 
1. In JENNIFER Dashboard, open the management area and Navigate to  **Extension and Notice** > **Adapter and Plugin**
2. Make sure the **Adapter** tab is selected then click the **[+Add]** button
3. Click the **Save** button to add the adapter. 

<img width="700" src="https://private-user-images.githubusercontent.com/1277117/433623158-1a808315-974b-4f30-b8e9-cb28d5c5f74c.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NDQ2ODIyMDEsIm5iZiI6MTc0NDY4MTkwMSwicGF0aCI6Ii8xMjc3MTE3LzQzMzYyMzE1OC0xYTgwODMxNS05NzRiLTRmMzAtYjhlOS1jYjI4ZDVjNWY3NGMucG5nP1gtQW16LUFsZ29yaXRobT1BV1M0LUhNQUMtU0hBMjU2JlgtQW16LUNyZWRlbnRpYWw9QUtJQVZDT0RZTFNBNTNQUUs0WkElMkYyMDI1MDQxNSUyRnVzLWVhc3QtMSUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LURhdGU9MjAyNTA0MTVUMDE1MTQxWiZYLUFtei1FeHBpcmVzPTMwMCZYLUFtei1TaWduYXR1cmU9OGZjZDU3YzNlZDBhY2E3ZDc1YzUwMmZiNzkwOTEwOGEwMTFkZmNkYTY4MjQ4Y2YwYzZmYmI2YzFkZGZkMzAzNyZYLUFtei1TaWduZWRIZWFkZXJzPWhvc3QifQ.ZwriWPsYc0oiJLaBucS1j45oW22hAPzZJT_MXq35KZc"/>


### 2- Adapter Options

Next step is to add the adapter options. There are 2 required options that you must configure, the **slack_webhook** and the **slack_channel* option. 
The rest of the options are optional. Refer the table below for the full list of available options for this adapter.

<img width="700" alt="slack_adapter_options" src="https://user-images.githubusercontent.com/3861725/27722333-eef01af0-5da1-11e7-8235-c993c88580af.png">

The following table shows the available options for this adapter

| Key           | Required      | Description |  Default Value 
| ------------- |:-------------:|:-------------:|:-------------:|
| webhook_url   | YES           | Set Teams Incoming Webhook URL | None 
| jennifer_url  | NO            | Optional: Set JENNIFER Share URL for the X-View pop-up. If the URL value is set, then this adapter will attempt to generate link to view the transactions in X-View and display the link in the slack message | None 


The following is an example of the slack message received from this adapter.

![slackexample](https://user-images.githubusercontent.com/3861725/49065611-d723d780-f261-11e8-98ee-8073638b9f7a.png)
