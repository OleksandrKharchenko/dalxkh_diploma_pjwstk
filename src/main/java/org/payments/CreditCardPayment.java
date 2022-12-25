package org.payments;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="CreditCardPayment")
public class CreditCardPayment extends Payment{
}
