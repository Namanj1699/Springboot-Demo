package com.example.demo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "payment.provider", havingValue = "stripe")
public class StripePaymentService implements PaymentService {

    @Override
    public String pay()
    {
        String payment = "Stripe Payment";
        System.out.println("Payment from :"+payment);
        return payment;
    }
}
