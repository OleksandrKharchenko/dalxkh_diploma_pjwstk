package org.payments;

import org.main.HibernateCommitsSpawner;
import org.orders.Order;

public abstract class PaymentOrderService {
    public static void createPayment(Order order, String typeOfPayment){
        if(order.getPayment() == null){
            if (typeOfPayment.equals("crypto")){
                Payment payment = new CryptoPayment(order);
                order.setPayment(payment);
                order.setState(payment.getState());
                HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
                spawner.updateCommit(order);
            }
            if (typeOfPayment.equals("card")){
                Payment payment = new CreditCardPayment(order, "telegramPayAPI");
                order.setPayment(payment);
                order.setState(payment.getState());
                HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
                spawner.updateCommit(order);
            }
        }
    }

    public static void completePayment(Payment payment){
        //LOGIC FOR CRYPTO

        //
        //LOGIC FOR CREDITCARD

        //
        payment.setCompleted(true);
        payment.setState("completed");
        payment.getOrder().setState("payment completed");
        HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
        spawner.updateCommit(payment);
        spawner.updateCommit(payment.getOrder());
    }

    public static void cancelPayment(Order order){
        if(order.getPayment() != null){
            HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
            order.getPayment().setState("canceled");
            spawner.updateCommit(order.getPayment());
            order.setPayment(null);
            spawner.updateCommit(order);
        }
    }
}
