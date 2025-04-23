/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.testing;

/**
 *
 * @author maryamasif
 */

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

// Interface for Notifications
interface Notifiable {
    void send(String message, String recipient) throws NotificationException;
}

// Custom Exception for Notification Errors
class NotificationException extends Exception {
    public NotificationException(String message) {
        super(message);
    }
}

// Abstract class for Notification
abstract class Notification implements Notifiable {
    protected String sender;

    public Notification(String sender) {
        this.sender = sender;
    }
}

// Email Utility Class
class EmailUtil {
    public static void sendEmail(String from, String password, String to, String subject, String messageText) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(messageText);

        Transport.send(message);
    }
}

// Email Notification Implementation
class EmailNotification extends Notification {
    private String senderEmail;
    private String senderPassword;

    public EmailNotification(String sender, String senderEmail, String senderPassword) {
        super(sender);
        this.senderEmail = senderEmail;
        this.senderPassword = senderPassword;
    }

    @Override
    public void send(String message, String recipient) throws NotificationException {
        if (recipient == null || recipient.isEmpty()) {
            throw new NotificationException("Email recipient is invalid.");
        }

        try {
            EmailUtil.sendEmail(senderEmail, senderPassword, recipient, "Notification from " + sender, message);
            System.out.println("[Email Sent] To: " + recipient);
        } catch (MessagingException e) {
            throw new NotificationException("Failed to send email: " + e.getMessage());
        }
    }
}

// SMS Notification (simulation)
class SMSNotification extends Notification {
    public SMSNotification(String sender) {
        super(sender);
    }

    @Override
    public void send(String message, String recipient) throws NotificationException {
        if (recipient == null || recipient.isEmpty()) {
            throw new NotificationException("SMS recipient is invalid.");
        }
        System.out.println("[SMS] From: " + sender + ", To: " + recipient + ", Message: " + message);
    }
}

// Notification Service
class NotificationService {
    private List<Notifiable> notifiers = new ArrayList<>();
    private List<String> emergencyContacts = new ArrayList<>();

    public void addNotifier(Notifiable notifier) {
        notifiers.add(notifier);
    }

    public void addEmergencyContact(String contact) {
        emergencyContacts.add(contact);
    }

    public void notifyAll(String message) {
        for (String recipient : emergencyContacts) {
            for (Notifiable notifier : notifiers) {
                try {
                    notifier.send(message, recipient);
                } catch (NotificationException e) {
                    System.out.println("Error sending notification: " + e.getMessage());
                }
            }
        }
    }
}

// Vitals Class
class Vitals {
    private double heartRate;
    private double bloodPressure;
    private double temperature;
    private double oxygenLevel;

    public Vitals(double heartRate, double bloodPressure, double temperature, double oxygenLevel) {
        this.heartRate = heartRate;
        this.bloodPressure = bloodPressure;
        this.temperature = temperature;
        this.oxygenLevel = oxygenLevel;
    }

    public double getHeartRate() {
        return heartRate;
    }

    public double getBloodPressure() {
        return bloodPressure;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getOxygenLevel() {
        return oxygenLevel;
    }
}

// Emergency Alert Class
class EmergencyAlert {
    private NotificationService notificationService;

    public EmergencyAlert(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void checkVitals(Vitals vitals) throws Exception {
        if (vitals.getHeartRate() > 100) {
            throw new Exception("Critical heart rate detected!");
        } else if (vitals.getBloodPressure() > 140) {
            throw new Exception("Critical blood pressure detected!");
        } else if (vitals.getTemperature() > 39.0) {
            throw new Exception("Critical temperature detected!");
        } else if (vitals.getOxygenLevel() < 90) {
            throw new Exception("Critical oxygen level detected!");
        }
    }
}

// Panic Button Class
class PanicButton {
    private EmergencyAlert emergencyAlert;
    private NotificationService notificationService;

    public PanicButton(EmergencyAlert emergencyAlert, NotificationService notificationService) {
        this.emergencyAlert = emergencyAlert;
        this.notificationService = notificationService;
    }

    public void press(double heartRate, double bloodPressure, double temperature, double oxygenLevel) {
        Vitals inputVitals = new Vitals(heartRate, bloodPressure, temperature, oxygenLevel);
        try {
            emergencyAlert.checkVitals(inputVitals);
        } catch (Exception e) {
            notificationService.notifyAll(e.getMessage());
        }
    }
}

// Chat Server
class ChatServer {
    public void sendMessage(String message, String from, String to) {
        System.out.println("[ChatServer] " + from + " to " + to + ": " + message);
    }

    public void sendGroupMessage(String message, String from, List<String> toGroup) {
        for (String recipient : toGroup) {
            sendMessage(message, from, recipient);
        }
    }
}

// Chat Client
class ChatClient {
    private String name;
    private ChatServer server;

    public ChatClient(String name, ChatServer server) {
        this.name = name;
        this.server = server;
    }

    public void sendChat(String message, String to) {
        server.sendMessage(message, this.name, to);
    }

    public void receiveChat(String message, String from) {
        System.out.println("[ChatClient] Received from " + from + ": " + message);
    }

    public void startVideoCall() {
        String videoLink = "https://meet.google.com/xyz-abc-def";
        System.out.println("Starting video call: " + videoLink);
    }
}

// Reminder Service
class ReminderService {
    private List<Notifiable> notifiers = new ArrayList<>();

    public void addNotifier(Notifiable notifier) {
        notifiers.add(notifier);
    }

    public void sendReminder(String message, String recipient) {
        for (Notifiable notifier : notifiers) {
            try {
                notifier.send(message, recipient);
            } catch (NotificationException e) {
                System.out.println("Error sending notification: " + e.getMessage());
            }
        }
    }
}



public class Testing {

    public static void main(String[] args) {
        // Updated email credentials for testing
        String senderEmail = "saniahammad1234@gmail.com";  // Replace with your Gmail
        String senderPassword = "wvdf fefg dccr wgax"; // Your password

        // Notification Service setup
        NotificationService notificationService = new NotificationService();
        notificationService.addNotifier(new EmailNotification("Hospital System", senderEmail, senderPassword));
        notificationService.addNotifier(new SMSNotification("Hospital System"));

        // Adding emergency contacts
        notificationService.addEmergencyContact("doctor@hospital.com");
        notificationService.addEmergencyContact("emergency@hospital.com");

        // Create an instance of EmergencyAlert and PanicButton
        EmergencyAlert emergencyAlert = new EmergencyAlert(notificationService);
        PanicButton panicButton = new PanicButton(emergencyAlert, notificationService);

        // Input patient's vitals
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter patient vitals:");
        System.out.print("Heart Rate: ");
        double heartRate = scanner.nextDouble();
        System.out.print("Blood Pressure: ");
        double bloodPressure = scanner.nextDouble();
        System.out.print("Temperature: ");
        double temperature = scanner.nextDouble();
        System.out.print("Oxygen Level: ");
        double oxygenLevel = scanner.nextDouble();

        // Press the panic button
        panicButton.press(heartRate, bloodPressure, temperature, oxygenLevel);

        // Clear scanner buffer
        scanner.nextLine();

        // Simulate chat communication between Doctor and Patient
        ChatServer chatServer = new ChatServer();
        ChatClient doctor = new ChatClient("Doctor", chatServer);
        ChatClient patient = new ChatClient("Patient", chatServer);

        // Doctor sends a message to the patient
        System.out.print("Doctor, enter your message to Patient: ");
        String doctorMsg = scanner.nextLine();
        doctor.sendChat(doctorMsg, "Patient");

        // Patient sends a reply to the doctor
        System.out.print("Patient, enter your reply to Doctor: ");
        String patientMsg = scanner.nextLine();
        patient.sendChat(patientMsg, "Doctor");

        // Start a video call for the patient
        patient.startVideoCall();

        // Reminder Section
        ReminderService reminderService = new ReminderService();

        // Ask if the user wants to send an email reminder
        System.out.print("Would you like to send an email reminder (yes/no)? ");
        String emailChoice = scanner.nextLine().toLowerCase();
        if (emailChoice.equals("yes")) {
            reminderService.addNotifier(new EmailNotification("Hospital System", senderEmail, senderPassword));
        }

        // Ask if the user wants to send an SMS reminder
        System.out.print("Would you like to send an SMS reminder (yes/no)? ");
        String smsChoice = scanner.nextLine().toLowerCase();
        if (smsChoice.equals("yes")) {
            reminderService.addNotifier(new SMSNotification("Hospital System"));
        }

        // Loop to send multiple reminders if needed
        String continueSending;
        do {
            System.out.print("Enter the reminder message: ");
            String reminderMessage = scanner.nextLine();

            System.out.print("Enter the recipient's contact (email/phone): ");
            String recipient = scanner.nextLine();

            // Send reminder
            reminderService.sendReminder(reminderMessage, recipient);

            // Ask if the user wants to send another reminder
            System.out.print("Would you like to send another reminder (yes/no)? ");
            continueSending = scanner.nextLine().toLowerCase();
        } while (continueSending.equals("yes"));

        // Close the scanner to prevent resource leak
        scanner.close();
    }
}
