package org.example.paymentservice.domain;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentApproved(
        UUID paymentId,
        UUID orderId,
        BigDecimal amount
) {
}
