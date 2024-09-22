package com.woorifisa.backend.subscription.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMail {
    
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String text){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
            System.out.println("Mail sent successfully");
        } catch (Exception e) {
            System.out.println("Failed to send mail: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
