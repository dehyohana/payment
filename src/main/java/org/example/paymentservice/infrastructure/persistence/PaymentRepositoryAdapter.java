package org.example.paymentservice.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import org.example.paymentservice.domain.Payment;
import org.example.paymentservice.domain.PaymentRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PaymentRepositoryAdapter implements PaymentRepository {
    private final JpaPaymentRepository repository;

    @Override
    public Payment save(Payment payment) {
        PaymentEntity entity = PaymentEntity.builder()
                .id(payment.getId())
                .orderId(payment.getOrderId())
                .amount(payment.getAmount())
                .status(payment.getStatus())
                .build();

        repository.save(entity);
        return payment;
    }

    @Override
    public Optional<Payment> findById(UUID orderId) {
        return repository.findByOrderId(orderId)
                .map(this::toDomain);
    }

    @Override
    public boolean existsByOrderId(UUID orderId) {
        return repository.existsByOrderId(orderId);
    }

    private Payment toDomain(PaymentEntity entity) {

        return new Payment(
                entity.getId(),
                entity.getOrderId(),
                entity.getAmount()
        );
    }
}
