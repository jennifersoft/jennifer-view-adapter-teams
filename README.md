# Overview
This adapter will send EVENT notification to a teams board.

## Getting started

### 1- Adapter Installation
The first step is to register the adapter: 
1. In JENNIFER Dashboard, open the management area and Navigate to  **Settings** > **SMTP and Adapter (+DB Plan)**
2. Make sure the **Adapter** tab is selected then click the **[+Add]** button
3. Click the **Save** button to add the adapter. 

<img width="1950" alt="Image" src="https://github.com/user-attachments/assets/f912778b-4eae-4847-bf23-f3a9d5d4a285" />


### 2- Adapter Options

Next step is to add the adapter options. There are 2 required options that you must configure, the **slack_webhook** and the **slack_channel* option. 
The rest of the options are optional. Refer the table below for the full list of available options for this adapter.

<img width="1955" alt="Image" src="https://github.com/user-attachments/assets/3a8d57e2-9fb1-4fce-8fcf-03368f3765e5" />

The following table shows the available options for this adapter

| Key           | Required      | Description |  Default Value 
| ------------- |:-------------:|:-------------:|:-------------:|
| webhook_url   | YES           | Set Teams Incoming Webhook URL | None 
| jennifer_url  | NO            | Optional: Set JENNIFER View Server URL for the X-View pop-up | None 


The following is an example of the slack message received from this adapter.

<img width="961" alt="Image" src="https://github.com/user-attachments/assets/c0fff6ff-049d-4f1d-a42c-43a1f517fa5b" />
