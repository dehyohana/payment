package org.example.paymentservice.infrastructure.kafka.consumer;

import lombok.RequiredArgsConstructor;
import org.example.paymentservice.application.PaymentProcessor;
import org.example.paymentservice.application.dto.PaymentRequest;
import org.example.paymentservice.infrastructure.kafka.message.OrderCreatedMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreatedConsumer {
    private final PaymentProcessor paymentProcessor;

    @KafkaListener(
            topics = "order-created",
            groupId = "payment-group")

    public void receive(OrderCreatedMessage  message) {
        System.out.println("Mensagem recebida: " + message);


        paymentProcessor.execute(
                new PaymentRequest(
                        message.orderId(),
                        message.amount()
                )
        );
        System.out.println("Pagamento processado");
    }
}
