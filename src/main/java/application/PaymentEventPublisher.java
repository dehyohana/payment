package application;

import domain.PaymentApproved;
import domain.PaymentRejected;

public interface PaymentEventPublisher {
    void publish(PaymentApproved event);
    void publish(PaymentRejected event);
}
