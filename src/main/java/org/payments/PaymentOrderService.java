package org.payments;

import org.main.HibernateCommitsSpawner;
import org.orders.Order;

public abstract class PaymentOrderService {
    public static void createPayment(Order order, String typeOfPayment){
        if(order.getPayment() == null){
            if (typeOfPayment.equals("crypto")){
                Payment payment = new CryptoPayment(order);
                order.setPayment(payment);
                HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
                spawner.updateCommit(order);
            }
            if (typeOfPayment.equals("card")){
                Payment payment = new CreditCardPayment(order, "telegramPayAPI");
                order.setPayment(payment);
                HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
                spawner.updateCommit(order);
            }
        }
    }

    public static void cancelPayment(Order order){
        if(order.getPayment() != null){

        }
    }
}
