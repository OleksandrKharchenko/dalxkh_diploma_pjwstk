package org.payments;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.main.EnvVars;
import org.orders.Order;

@Entity
@DiscriminatorValue(value="CryptoPayment")
public class CryptoPayment extends Payment{
    @Column(name="cryptoPaymentAddress")
    private String cryptoPaymentAddress;
    @Column(name="blockchain")
    private String blockchain;
    @Column(name="amountInCrypto")
    private double amountInCrypto;

    public CryptoPayment() {
    }

    public CryptoPayment(Order order, String blockchain) {
        super(order);
        if (blockchain.equals("ETH")){
            this.cryptoPaymentAddress = EnvVars.DefaultCryptoPaymentAddress;
        }
        if (blockchain.equals("MATIC")){
            this.cryptoPaymentAddress = EnvVars.DefaultCryptoPaymentAddress;
        }
        this.cryptoPaymentAddress = cryptoPaymentAddress;
        this.blockchain = blockchain;
        this.amountInCrypto = order.getCryptoEquivalentPrice();
    }

    public CryptoPayment(Order order) {
        super(order);
        this.cryptoPaymentAddress = EnvVars.DefaultCryptoPaymentAddress;
        this.blockchain = EnvVars.DefaultBlockchain;
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

    public double getAmountInCrypto() {
        return amountInCrypto;
    }

    public void setAmountInCrypto(double amountInCrypto) {
        this.amountInCrypto = amountInCrypto;
    }

}
