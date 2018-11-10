package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailCreatorService mailCreatorService;

    public void send(Mail mail, MailMessageType messageType) {

        LOGGER.info("Starting email preparation...");

        try {
            if (messageType.equals(MailMessageType.SCHEDULE)) {
                javaMailSender.send(createMimeMessage(mail, MailMessageType.SCHEDULE));
            } else {
                javaMailSender.send(createMimeMessage(mail, MailMessageType.NORMAL));
            }
            LOGGER.info("Email has been sent");
        } catch (MailException e) {
            LOGGER.error("Failed to process email sending: ", e.getMessage(), e);
        }
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail, final MailMessageType messageType) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("kodilla999@gmail.com");
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(getMessageFromTemplate(mail.getMessage(), messageType), true);
            };
    }

    private String getMessageFromTemplate(final String message, final MailMessageType messageType) {
        switch (messageType) {
            case NORMAL:
                return mailCreatorService.buildTrelloCardEmail(message);
            case SCHEDULE:
                return mailCreatorService.buildScheduleEmail(message);
            default:
                return message;
        }
    }

    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()));
        return mailMessage;
    }
}
