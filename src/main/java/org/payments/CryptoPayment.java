package org.payments;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="CryptoPayment")
public class CryptoPayment extends Payment{
}
