//package com.eVolGreen.eVolGreen.Controllers;
//
//import com.eVolGreen.eVolGreen.Models.Email;
//import com.eVolGreen.eVolGreen.Services.EmailService;
//import jakarta.mail.MessagingException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping ("/api")
//public class EmailController {
//    @Autowired
//    private EmailService emailService;
//
//    @PostMapping("/email")
//    public ResponseEntity <String> sendEmail(@RequestBody Email email) throws MessagingException {
//        emailService.sendEmail(email);
//        return ResponseEntity.ok("Email sent successfully");
//    }
//}
