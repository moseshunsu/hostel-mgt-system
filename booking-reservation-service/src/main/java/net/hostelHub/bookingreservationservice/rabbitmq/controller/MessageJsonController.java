package net.hostelHub.bookingreservationservice.rabbitmq.controller;

import net.hostelHub.bookingreservationservice.dto.EmailDetails;
import net.hostelHub.bookingreservationservice.rabbitmq.publisher.RabbitMQJsonProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class MessageJsonController {

    private final RabbitMQJsonProducer jsonProducer;

    public MessageJsonController(RabbitMQJsonProducer jsonProducer) {
        this.jsonProducer = jsonProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody EmailDetails emailDetails) {
        jsonProducer.sendJsonMessage(emailDetails);
        return ResponseEntity.ok("Json message sent to RabbitMQ...");
    }

}
