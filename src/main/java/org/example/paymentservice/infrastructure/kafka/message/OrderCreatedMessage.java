package org.example.paymentservice.infrastructure.kafka.message;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderCreatedMessage(
        UUID orderId,
        BigDecimal amount
) {
}
