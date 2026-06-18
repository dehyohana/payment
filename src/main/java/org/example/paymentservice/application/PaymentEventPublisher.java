package org.example.paymentservice.application;

import org.example.paymentservice.domain.PaymentApproved;
import org.example.paymentservice.domain.PaymentRejected;

public interface PaymentEventPublisher {
    void publish(PaymentApproved event);
    void publish(PaymentRejected event);
}
