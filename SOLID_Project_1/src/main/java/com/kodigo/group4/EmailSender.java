package com.kodigo.group4;

import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSender {

    private String recipientEmail;
    private String senderEmail = "aeropuertoelsalvador@outlook.com";
    private String senderEmailPassword = "aeropuerto123456";
    private String filePath;
    private Date date = new Date();

    public EmailSender() {
    }

    public void sendEmail(String recipientEmail, String filePath){
        this.recipientEmail = recipientEmail;
        this.filePath = filePath;
        System.out.println("Initializing email...");
        Properties props = null;
        if (props == null) {
            props = new Properties();
            props.put("mail.smtp.auth", true);
            props.put("mail.smtp.starttls.enable", true);
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");
            props.put("mail.smtp.host", "smtp.live.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.user", senderEmail);
            props.put("mail.smtp.pwd", senderEmailPassword);
        }
        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderEmail, senderEmailPassword);
                    }
                });
        try{
            Message message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(senderEmail));
            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail));
            // Set Subject: header field
            message.setSubject("Flight(s) information");
            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();
            // Writing message
            messageBodyPart.setText("Greetings, Dear Client. \n" +
                    "As you selected, attached you can find a file which " +
                    "contains information about the flight/day you chosen. \n" +
                    "Thanks for using the Airport of El Salvador System.");
            // Creating a multipart message
            Multipart multipart = new MimeMultipart();
            // Set text message
            multipart.addBodyPart(messageBodyPart);
            // Attaching file
            messageBodyPart = new MimeBodyPart();
            String filename = "/Users/hectormontano/IdeaProjects/CSF_Project_1/SOLID_Project_1/GeneratedFiles/Flights-112345.xlsx";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("Flight(s) information - " + date +".xlsx");
            multipart.addBodyPart(messageBodyPart);
            // Send the complete message parts
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Email message was sent successfully....");
        }catch (MessagingException e){
            throw new RuntimeException(e);
        }
    }
}
