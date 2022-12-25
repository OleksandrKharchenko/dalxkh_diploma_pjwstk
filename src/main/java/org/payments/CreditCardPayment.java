package org.payments;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.orders.Order;


@Entity
@DiscriminatorValue(value="CreditCardPayment")
public class CreditCardPayment extends Payment{
    @Column(name="payAPI")
    private String payAPI;

    public CreditCardPayment() {

    }

    public CreditCardPayment(Order order, int amount, String payAPI) {
        super(order, amount);
        this.payAPI = payAPI;
    }
}
