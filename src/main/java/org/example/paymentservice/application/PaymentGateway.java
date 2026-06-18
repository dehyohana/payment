package org.example.paymentservice.application;

import org.example.paymentservice.domain.Payment;

public interface PaymentGateway {
    boolean process(Payment payment);
}
