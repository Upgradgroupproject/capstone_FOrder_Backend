package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Payment;
import org.upgrad.repositories.PaymentRepository;

import java.util.List;

/*
 * Services/ Methods to call Repository or SQL scripts, these services are called from Payment controller
 *
 * */


@Service
public class PaymentServiceImpl implements PaymentService{

    private PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> getPaymentMethods() {
        return paymentRepository.findPaymentMethods();
    }
}
