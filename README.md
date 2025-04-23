# Hospital System with Notifications and Communication Features

This project simulates a hospital system that includes emergency alerts, chat communication between doctors and patients, reminders, and more. It leverages Java for implementing features such as notification handling via email and SMS, panic button functionality, vitals checking, and a chat server/client setup.

## Features
- **Emergency Alert System**: Sends email/SMS notifications to emergency contacts if a patient's vitals (heart rate, blood pressure, temperature, oxygen level) are critical.
- **Chat Communication**: Simulates communication between a doctor and patient through a simple chat system.
- **Video Call Setup**: Provides a simulated video call link for patients.
- **Reminder Service**: Allows sending email and SMS reminders to contacts.

## Prerequisites
Before running this project, ensure you have the following installed:
- **Java** (version 8 or later).
- **Gmail account** for email notifications (You’ll need to enable less secure apps in your Gmail account for email functionality).

## Setup and Running the Project

### Step 1: Clone the Repository
Clone this repository to your local machine:

```bash
git clone https://github.com/username/repository-name.git
## Step 2: Compile the Java Files
Navigate to the project directory and compile the Java files:
cd repository-name
javac *.java
## Step 3: Run the Main Class
Run the main class (Testing) that simulates the hospital system:
java Testing
## Step 4: Follow the Prompts
Enter Patient Vitals: The program will prompt you to enter the patient's heart rate, blood pressure, temperature, and oxygen level.
If any of these vitals are critical, an emergency alert will be triggered.
Doctor and Patient Communication:
The system will allow you to send and receive messages between a doctor and a patient.
The patient can also start a simulated video call.
Sending Reminders:
After completing the communication, you will be prompted to send email/SMS reminders.
You can send multiple reminders to contacts by entering the message and recipient details.
