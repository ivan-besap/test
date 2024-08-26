//package com.eVolGreen.eVolGreen.Services.Implement;
//
//import com.eVolGreen.eVolGreen.Models.Email;
//import com.eVolGreen.eVolGreen.Services.EmailService;
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
//
//@Service
//public class EmailServiceImplement implements EmailService {
//    @Autowired
//    private JavaMailSender javaMailSender;
//    @Autowired
//    private TemplateEngine templateEngine;
//
//    public EmailServiceImplement(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
//        this.javaMailSender = javaMailSender;
//        this.templateEngine = templateEngine;
//    }
//
//    @Override
//    public void sendEmail(Email email) throws MessagingException {
//        MimeMessage message = javaMailSender.createMimeMessage();
//        try {
//
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setTo(email.getDestination());
//            helper.setSubject(email.getSubject());
//            // Configurar el contenido del correo electrónico
//            Context context = new Context();
//            context.setVariable("message", email.getBody());
//            String contentHtml = templateEngine.process("email", context);
//            helper.setText(contentHtml, true);
//
//            // Enviar el correo electrónico
//            javaMailSender.send(message);
//        } catch (Exception e) {
//            throw new RuntimeException("Error al enviar el correo electrónico: " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public void sendAccountActivationEmail(Email email) {
//        MimeMessage message = javaMailSender.createMimeMessage();
//        try {
//
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setTo(email.getDestination());
//            helper.setSubject(email.getSubject());
//            // Configurar el contenido del correo electrónico
//            Context context = new Context();
//            context.setVariable("message", email.getBody());
//            String contentHtml = templateEngine.process("accountValide", context);
//            helper.setText(contentHtml, true);
//
//            // Enviar el correo electrónico
//            javaMailSender.send(message);
//        } catch (Exception e) {
//            throw new RuntimeException("Error al enviar el correo electrónico: " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public void sendCarCreatedEmail(Email email) {
//        MimeMessage message = javaMailSender.createMimeMessage();
//        try {
//
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setTo(email.getDestination());
//            helper.setSubject(email.getSubject());
//            // Configurar el contenido del correo electrónico
//            Context context = new Context();
//            context.setVariable("message", email.getBody());
//            String contentHtml = templateEngine.process("newCar", context);
//            helper.setText(contentHtml, true);
//
//            // Enviar el correo electrónico
//            javaMailSender.send(message);
//        } catch (Exception e) {
//            throw new RuntimeException("Error al enviar el correo electrónico: " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public void sendCarDeletedEmail(Email email) {
//        MimeMessage message = javaMailSender.createMimeMessage();
//        try {
//
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setTo(email.getDestination());
//            helper.setSubject(email.getSubject());
//            // Configurar el contenido del correo electrónico
//            Context context = new Context();
//            context.setVariable("message", email.getBody());
//            String contentHtml = templateEngine.process("carDelete", context);
//            helper.setText(contentHtml, true);
//
//            // Enviar el correo electrónico
//            javaMailSender.send(message);
//        } catch (Exception e) {
//            throw new RuntimeException("Error al enviar el correo electrónico: " + e.getMessage(), e);
//        }
//    }
//}
