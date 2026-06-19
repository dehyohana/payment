package org.example.paymentservice.infrastructure.persistence;
import jakarta.persistence.*;
import lombok.*;
import org.example.paymentservice.domain.PaymentStatus;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentEntity {
    @Id
    private UUID id;

    private UUID orderId;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

}
