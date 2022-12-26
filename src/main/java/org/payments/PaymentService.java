package org.payments;

import org.hibernate.Session;
import org.main.HibernateSessionFactorySpawner;
import org.orders.Order;
import org.users.TelegramClientUser;
import org.webitems.WebItem;

public abstract class PaymentService {
    public static void createPayment(Order order, String typeOfPayment){
            if (typeOfPayment.equals("crypto")){
                Payment payment = new CryptoPayment(order);
                Session startCreatePaymentSession = HibernateSessionFactorySpawner.spawnSession();
                startCreatePaymentSession.beginTransaction();
                startCreatePaymentSession.persist(payment);
                startCreatePaymentSession.merge(order);
                startCreatePaymentSession.getTransaction().commit();
            }
            if (typeOfPayment.equals("card")){
                Payment payment = new CreditCardPayment(order, "telegramPayAPI");
                Session startCreatePaymentSession = HibernateSessionFactorySpawner.spawnSession();
                startCreatePaymentSession.beginTransaction();
                startCreatePaymentSession.persist(payment);
                startCreatePaymentSession.getTransaction().commit();
            }

    }
}
