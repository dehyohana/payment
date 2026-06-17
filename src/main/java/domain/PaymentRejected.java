package domain;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentRejected(
        UUID paymentId,
        UUID orderId,
        String reason
) {
}
