package org.payments;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.orders.Order;

@Entity
@DiscriminatorValue(value="CryptoPayment")
public class CryptoPayment extends Payment{
    @Column(name="cryptoPaymentAddress")
    private String cryptoPaymentAddress;
    @Column(name="blockchain")
    private String blockchain;

    public CryptoPayment() {
    }

    public CryptoPayment(Order order, int amount, String cryptoPaymentAddress, String blockchain) {
        super(order, amount);
        this.cryptoPaymentAddress = cryptoPaymentAddress;
        this.blockchain = blockchain;
    }

    public String getCryptoPaymentAddress() {
        return cryptoPaymentAddress;
    }

    public void setCryptoPaymentAddress(String cryptoPaymentAddress) {
        this.cryptoPaymentAddress = cryptoPaymentAddress;
    }

    public String getBlockchain() {
        return blockchain;
    }

    public void setBlockchain(String blockchain) {
        this.blockchain = blockchain;
    }
}
