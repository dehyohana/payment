package org.example.paymentservice.application;

import org.example.paymentservice.application.dto.PaymentRequest;

public interface PaymentProcessor {

    void execute(PaymentRequest request);
}
