package org.example.paymentservice.infrastructure.kafka.message;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record  PaymentApprovedMessage(
        UUID paymentId,
        UUID orderId,
        BigDecimal amount,
        Instant processedAt
) {
}
