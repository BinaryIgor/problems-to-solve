# Device sales process

We have an application that allows users to sell their used devices:

1. Users select a device category and model.
2. They answer several questions about the device's condition. Based on their answers, the device is assigned one of five grades.
3. They accept or reject the valuation.

Design a solution for the following workflow:

1. When a user accepts a valuation, create a transaction via the API.
2. For every transaction, we must:
   1. Generate a shipping label that the user can use to send us their device.
   2. Store the shipping label so that it can be retained for auditing and made available to the user in the app.
   3. Once generated, send the shipping label to the user by email.