package net.hostelHub.notificationservice.rabbitmq.consumer;

import net.hostelHub.notificationservice.email.dto.EmailDetails;
import net.hostelHub.notificationservice.email.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQEmailConsumer {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "${rabbitmq.occupant.booking.queue}")
    public void sendUserRegistrationDetails(EmailDetails emailDetails) {
            emailService.sendSimpleMail(emailDetails);
    }

}