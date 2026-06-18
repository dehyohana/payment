package application;

import application.dto.PaymentRequest;
import domain.Payment;
import domain.PaymentApproved;
import domain.PaymentRejected;
import domain.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentProcessor{


    private final PaymentRepository repository;
    private final PaymentGateway paymentGateway;
    private final PaymentEventPublisher publisher;


    @Override
    public void execute(PaymentRequest request) {

        if (repository.existsByOrderId(request.orderId())) {
            return;
        }

        Payment payment = new Payment(
                UUID.randomUUID(),
                request.orderId(),
                request.amount()
        );

        boolean aproved = paymentGateway.process(payment);

        if (aproved) {
            PaymentApproved event = payment.approve();
            repository.save(payment);
            publisher.publish(event);
        }
        else {
            PaymentRejected evemt = payment.reject("Saldo insuficiente");
            repository.save(payment);
            publisher.publish(evemt);
        }

    }
}
