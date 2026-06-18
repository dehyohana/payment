package application;

import domain.Payment;

public interface PaymentGateway {
    boolean process(Payment payment);
}
