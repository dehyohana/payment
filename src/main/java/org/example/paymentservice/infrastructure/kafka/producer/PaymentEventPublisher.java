package org.example.paymentservice.infrastructure.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.example.paymentservice.domain.PaymentApproved;
import org.example.paymentservice.domain.PaymentRejected;
import org.example.paymentservice.infrastructure.kafka.message.PaymentApprovedMessage;
import org.example.paymentservice.infrastructure.kafka.message.PaymentRejectedMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class PaymentEventPublisher implements org.example.paymentservice.application.PaymentEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publish(PaymentApproved event) {
        System.out.println("Publicando payment-approved");

        PaymentApprovedMessage message = new PaymentApprovedMessage(
                event.paymentId(),
                event.orderId(),
                event.amount(),
                Instant.now()
        );
        kafkaTemplate.send("payment-approved", message);

    }

    @Override
    public void publish(PaymentRejected event) {
        PaymentRejectedMessage message = new PaymentRejectedMessage(
                event.paymentId(),
                event.orderId(),
                event.reason(),
                Instant.now()
        );
        kafkaTemplate.send("payment-rejected", message);

    }
}
