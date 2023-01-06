package org.payments;

import jakarta.persistence.*;
import org.main.HibernateCommitsSpawner;
import org.orders.OrderCryptoReceiverSenderService;

import java.io.IOException;

@Entity
@Table(name="CryptoBalance")
public class CryptoPaymentBalance {
    @Id
    @Column(name="idBalance")
    private int idBalance;
    @Column(name="balance")
    private double balance;

    public CryptoPaymentBalance() throws IOException {
        OrderCryptoReceiverSenderService orderCryptoSenderService = new OrderCryptoReceiverSenderService();
        this.balance = orderCryptoSenderService.getBalance();
    }

    public void updateBalance(){
        HibernateCommitsSpawner hibernateCommitsSpawner = new HibernateCommitsSpawner();
        hibernateCommitsSpawner.updateCommit(this);
    }

    public void createBalance(){
        HibernateCommitsSpawner hibernateCommitsSpawner = new HibernateCommitsSpawner();
        hibernateCommitsSpawner.createCommit(this);
    }

}
