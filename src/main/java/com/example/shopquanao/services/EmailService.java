package com.example.shopquanao.services;

import java.util.Date;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.shopquanao.configs.AppConfig;
import com.example.shopquanao.exceptions.CommonException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	private final JavaMailSender mailSender;
	private final AppConfig appConfig;
	private final RedisService redisService;

	public EmailService(AppConfig appConfig,JavaMailSender mailSender, RedisService redisService) {
		this.appConfig = appConfig;
		this.mailSender = mailSender;
		this.redisService = redisService;
	}
	
	public void sendVerificationEmail(String toEmail, String token) {
		Date now = new Date();
        String subject = now.toString() + " | Verify your account";
        
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String shortCode = uuid.substring(0, 16);
        
        redisService.saveToken(shortCode, token, 15);
        
        String verificationLink = appConfig.getAppFE() + "/verify-enail?code=" + shortCode;
        
        String body = "<div style=\"font-family: Arial, sans-serif;\">"
                + "<h2>Welcome!</h2>"
                + "<p>Please verify your email by clicking the button below</p>"
                + "<a href=\"" + verificationLink + "\" target=\"_blank\" "
                + "style=\"display: inline-block; background-color: #28a745; color: white; "
                + "padding: 12px 20px; text-decoration: none; font-size: 16px; border-radius: 5px; "
                + "box-shadow: 0 4px 6px rgba(0,0,0,0.1);\">"
                + "Verify Email</a>"
                + "<p>Or copy code: " + shortCode + "</p>"
                + "<p>If you did not request this, please ignore this email.</p>"
                + "</div>";

        sendEmail(toEmail, subject, body);
    }

    private void sendEmail(String toEmail, String subject, String body) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body, true);

            mailSender.send(message);
        } catch (MessagingException e) {
        	throw new CommonException(HttpStatus.NOT_FOUND, "Error sending email!");
        }
    }
}
