package org.example.paymentservice.application;

import org.example.paymentservice.application.dto.PaymentRequest;
import org.example.paymentservice.domain.Payment;
import org.example.paymentservice.domain.PaymentApproved;
import org.example.paymentservice.domain.PaymentRejected;
//import org.example.paymentservice.domain.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentProcessor{


//    private final PaymentRepository repository;
//    private final PaymentGateway paymentGateway;
    private final PaymentEventPublisher publisher;


    @Override
    public void execute(PaymentRequest request) {

//        if (repository.existsByOrderId(request.orderId())) {
//            return;
//        }

        Payment payment = new Payment(
                UUID.randomUUID(),
                request.orderId(),
                request.amount()
        );

//        boolean approved = paymentGateway.process(payment);

//        if (approved) {
            PaymentApproved event = payment.approve();
//            repository.save(payment);
            publisher.publish(event);
//        }
//        else {
//            PaymentRejected event = payment.reject("Saldo insuficiente");
////            repository.save(payment);
//            publisher.publish(event);
//        }

    }
}
