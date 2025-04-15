# Overview
This adapter will send EVENT notification to a teams board.

## Getting started

### 1- Adapter Installation
The first step is to register the adapter: 
1. In JENNIFER Dashboard, open the management area and Navigate to  **Settings** > **SMTP and Adapter (+DB Plan)**
2. Make sure the **Adapter** tab is selected then click the **[+Add]** button
3. Click the **Save** button to add the adapter. 

<img width="1024" src="[https://private-user-images.githubusercontent.com/1277117/433623158-1a808315-974b-4f30-b8e9-cb28d5c5f74c.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NDQ2ODIyMDEsIm5iZiI6MTc0NDY4MTkwMSwicGF0aCI6Ii8xMjc3MTE3LzQzMzYyMzE1OC0xYTgwODMxNS05NzRiLTRmMzAtYjhlOS1jYjI4ZDVjNWY3NGMucG5nP1gtQW16LUFsZ29yaXRobT1BV1M0LUhNQUMtU0hBMjU2JlgtQW16LUNyZWRlbnRpYWw9QUtJQVZDT0RZTFNBNTNQUUs0WkElMkYyMDI1MDQxNSUyRnVzLWVhc3QtMSUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LURhdGU9MjAyNTA0MTVUMDE1MTQxWiZYLUFtei1FeHBpcmVzPTMwMCZYLUFtei1TaWduYXR1cmU9OGZjZDU3YzNlZDBhY2E3ZDc1YzUwMmZiNzkwOTEwOGEwMTFkZmNkYTY4MjQ4Y2YwYzZmYmI2YzFkZGZkMzAzNyZYLUFtei1TaWduZWRIZWFkZXJzPWhvc3QifQ.ZwriWPsYc0oiJLaBucS1j45oW22hAPzZJT_MXq35KZc](https://private-user-images.githubusercontent.com/1277117/433630060-842dd0d4-0c16-4139-bfcc-9b2a3687e08e.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NDQ2ODM5MjcsIm5iZiI6MTc0NDY4MzYyNywicGF0aCI6Ii8xMjc3MTE3LzQzMzYzMDA2MC04NDJkZDBkNC0wYzE2LTQxMzktYmZjYy05YjJhMzY4N2UwOGUucG5nP1gtQW16LUFsZ29yaXRobT1BV1M0LUhNQUMtU0hBMjU2JlgtQW16LUNyZWRlbnRpYWw9QUtJQVZDT0RZTFNBNTNQUUs0WkElMkYyMDI1MDQxNSUyRnVzLWVhc3QtMSUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LURhdGU9MjAyNTA0MTVUMDIyMDI3WiZYLUFtei1FeHBpcmVzPTMwMCZYLUFtei1TaWduYXR1cmU9MGVlMzkwNjllNDliNWVhNmZjYmQ1NTQxZmE0MTI2ODllOTMxM2I2Mjc5MzQ0YzgxYzYzOTVlZjRhMTJiNGUwNSZYLUFtei1TaWduZWRIZWFkZXJzPWhvc3QifQ.FCLfa58RWB4oLR8YJyzPavIz4h7cU9y-qpASdMXpvs0)"/>


### 2- Adapter Options

Next step is to add the adapter options. There are 2 required options that you must configure, the **slack_webhook** and the **slack_channel* option. 
The rest of the options are optional. Refer the table below for the full list of available options for this adapter.

<img width="1024" src="https://private-user-images.githubusercontent.com/1277117/433623156-fb6cc4fa-c84e-4957-bff8-82a17722d5bc.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NDQ2ODIzNjAsIm5iZiI6MTc0NDY4MjA2MCwicGF0aCI6Ii8xMjc3MTE3LzQzMzYyMzE1Ni1mYjZjYzRmYS1jODRlLTQ5NTctYmZmOC04MmExNzcyMmQ1YmMucG5nP1gtQW16LUFsZ29yaXRobT1BV1M0LUhNQUMtU0hBMjU2JlgtQW16LUNyZWRlbnRpYWw9QUtJQVZDT0RZTFNBNTNQUUs0WkElMkYyMDI1MDQxNSUyRnVzLWVhc3QtMSUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LURhdGU9MjAyNTA0MTVUMDE1NDIwWiZYLUFtei1FeHBpcmVzPTMwMCZYLUFtei1TaWduYXR1cmU9ZGRiM2U3YjFhMTMxMDRkOThkNDQzYmZlNTRiMDA0ZmJkMDJiNDFkNWYxODg4NWUyMTAxM2I5ZmYwYzBiZDdlMiZYLUFtei1TaWduZWRIZWFkZXJzPWhvc3QifQ.iQBYgz5nIyjD2Pl7w_FJqMq1vxaxdoFDIXEHzwJHWGo">

The following table shows the available options for this adapter

| Key           | Required      | Description |  Default Value 
| ------------- |:-------------:|:-------------:|:-------------:|
| webhook_url   | YES           | Set Teams Incoming Webhook URL | None 
| jennifer_url  | NO            | Optional: Set JENNIFER View Server URL for the X-View pop-up | None 


The following is an example of the slack message received from this adapter.

<img width="1024" src="https://private-user-images.githubusercontent.com/1277117/433623157-93e1ab17-1dcc-4ec7-9695-6668b7c1aa65.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NDQ2ODIzNjAsIm5iZiI6MTc0NDY4MjA2MCwicGF0aCI6Ii8xMjc3MTE3LzQzMzYyMzE1Ny05M2UxYWIxNy0xZGNjLTRlYzctOTY5NS02NjY4YjdjMWFhNjUucG5nP1gtQW16LUFsZ29yaXRobT1BV1M0LUhNQUMtU0hBMjU2JlgtQW16LUNyZWRlbnRpYWw9QUtJQVZDT0RZTFNBNTNQUUs0WkElMkYyMDI1MDQxNSUyRnVzLWVhc3QtMSUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LURhdGU9MjAyNTA0MTVUMDE1NDIwWiZYLUFtei1FeHBpcmVzPTMwMCZYLUFtei1TaWduYXR1cmU9Y2JlNDRiNzFhOGFkMjFmNTE0OTRmOTk5YjIyOTIyMjdiZTUzOTc2NTJlNDczMjVmZTlmMWQyZTBkYmNkZTI5OSZYLUFtei1TaWduZWRIZWFkZXJzPWhvc3QifQ.8B-i8KZ0Sm9uLSA8wW9RdUq2l_59Kzu1KA_vEPPwVK8">
