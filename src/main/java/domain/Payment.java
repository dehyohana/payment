package domain;

import java.math.BigDecimal;
import java.util.UUID;

public class Payment {
    private UUID id;
    private UUID orderId;
    private BigDecimal amount;
    private PaymentStatus status;

    public Payment(
            UUID id,
            UUID orderId,
            BigDecimal amount) {
        validateAmount(amount);

        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.status = PaymentStatus.PENDING;
    }

    public PaymentApproved approve() {
        validatePending();
        this.status = PaymentStatus.APPROVED;

        return new PaymentApproved(id, orderId, amount);
    }

    public PaymentRejected reject(String reason) {
        validatePending();
        this.status = PaymentStatus.REJECTED;
        return new PaymentRejected(id, orderId, reason);
    }


    private void validatePending() {
        if (status != PaymentStatus.PENDING) {
            throw new BusinessException("Pagamento já processado.");
        }
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("Valor inválido.");
        }
    }

    public UUID getId() {
        return id;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }
}
