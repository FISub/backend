package com.woorifisa.backend.subscription.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import java.io.File;

@Service
public class SendMail {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String text) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8"); // true를 설정하여 멀티파트 활성화

            helper.setTo(to);
            helper.setSubject(subject);

            // HTML 본문 설정, 이미지를 CID로 참조
            String htmlMsg = "<html><body>" + text + "<br><br><img src='cid:logoImage' style='width:50%;'></body></html>";

            helper.setText(htmlMsg, true); // true로 설정하여 HTML 내용을 허용

            // CID로 참조되는 이미지 첨부
            FileSystemResource res = new FileSystemResource(new File("src/main/resources/static/Logo-removebg-preview.png"));
            helper.addInline("logoImage", res);

            mailSender.send(message);
            System.out.println("Mail with inline image sent successfully");
        } catch (Exception e) {
            System.out.println("Failed to send mail with inline image: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
