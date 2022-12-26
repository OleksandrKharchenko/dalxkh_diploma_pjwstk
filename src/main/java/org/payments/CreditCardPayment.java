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
    @Column(name="amountInUSD")
    private int amountInUSD;

    public CreditCardPayment() {

    }

    public CreditCardPayment(Order order, String payAPI) {
        super(order);
        this.payAPI = payAPI;
        this.amountInUSD = order.getUsdEquivalentPrice();
    }
}
