package net.hostelHub.notificationservice.email.service;

import net.hostelHub.notificationservice.email.dto.EmailDetails;

public interface EmailService {

    String sendSimpleMail(EmailDetails details);
    String sendMailWithAttachment(EmailDetails details);

}
