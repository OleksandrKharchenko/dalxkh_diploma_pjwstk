package org.orders;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class OrderEmailSenderService {

    public static void sendMailTo(String mailTo, String redeemCode){

    String to = mailTo;
    String from = "s15638@pjwstk.edu.pl";
    String host = "smtp.gmail.com";
    Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

    Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

        protected PasswordAuthentication getPasswordAuthentication() {

            return new PasswordAuthentication("s15638@pjwstk.edu.pl", "curbqihdwaulqwzd");

        }

    });

        try {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("InetItemStore. Order completed.");
        message.setText("This is your code: " + redeemCode);
        System.out.println("sending email...");
        Transport.send(message);
        System.out.println("Sent email successfully....");
    } catch (MessagingException me) {
        me.printStackTrace();
    }

    }
}
