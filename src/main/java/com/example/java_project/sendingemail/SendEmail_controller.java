//This is only for practice ..

package com.example.java_project.sendingemail;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail_controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField emailMsg;


    public void sendEmails(javafx.event.ActionEvent actionEvent) {

        String from = "hemankkumar92@gmail.com"; // sender's email
        final String username = "hemankkumar92@gmail.com"; // your Gmail address
        final String password = "ruob zghg dckl dpwr"; // your app password

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username,password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("hemankkumar221@gmail.com"));
            message.setSubject("Email from Stitch Works");
            String emailBody = emailMsg.getText();
            message.setText(emailBody);
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void initialize() {
        assert emailMsg != null : "fx:id=\"emailMsg\" was not injected: check your FXML file 'mail.fxml'.";

    }
}
