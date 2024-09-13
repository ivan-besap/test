package com.eVolGreen.eVolGreen.Services.AccountService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String to, String verificationUrl) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("jose@beri.cl");
            helper.setTo(to);
            helper.setSubject("Verificación de cuenta");

            String htmlMsg = "<p>Haz clic en el siguiente enlace para verificar tu cuenta:</p>"
                    + "<a href=\"" + verificationUrl + "\">Verificar cuenta</a>";
            helper.setText(htmlMsg, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}