package application;

import application.dto.PaymentRequest;

public interface PaymentProcessor {

    void execute(PaymentRequest request);
}
