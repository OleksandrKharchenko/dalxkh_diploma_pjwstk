package org.payments;

import org.main.HibernateCommitsSpawner;
import org.orders.Order;

public class PaymentOrderService {
    public Payment createPayment(Order order, String typeOfPayment){
        if(order.getPayment() == null){
            if (typeOfPayment.equals("crypto")){
                Payment payment = new CryptoPayment(order, "ETH");
                order.setPayment(payment);
                order.setState(payment.getState());
                HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
                spawner.updateCommit(order);
                return payment;
            }
            if (typeOfPayment.equals("fiat")){
                Payment payment = new FiatPayment(order, "PayAPI");
                order.setPayment(payment);
                order.setState(payment.getState());
                HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
                spawner.updateCommit(order);
                return payment;
            }
        }
        return null;
    }

    public void completePaymentCrypto(Payment payment, String txHash) throws Exception {
        //LOGIC FOR CRYPTO
            payment.setCompleted(true);
            payment.setState("completed");
            ((CryptoPayment) payment).setTxHashVerified(txHash);
            payment.getOrder().setState("payment completed");
            HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
            spawner.updateCommit(payment);
            spawner.updateCommit(payment.getOrder());
        }


    public void completePaymentFiat(Payment payment) {
        //LOGIC FOR CREDIT CARD
            payment.setCompleted(true);
            payment.setState("completed");
            payment.getOrder().setState("payment completed");
            HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
            spawner.updateCommit(payment);
            spawner.updateCommit(payment.getOrder());
        }

    public void cancelPayment(Order order){
        if(order.getPayment() != null){
            HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
            order.getPayment().setState("canceled");
            spawner.updateCommit(order.getPayment());
            order.setPayment(null);
            spawner.updateCommit(order);
        }
    }
}
