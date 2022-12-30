package org.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.users.*;
import org.webitems.*;
import org.orders.*;
import org.payments.*;

public class HibernateSessionFactorySpawner {

    static Session session;
    static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml");
        configObj.addAnnotatedClass(User.class).
                addAnnotatedClass(TelegramUser.class).
                addAnnotatedClass(TelegramOperationalUser.class).
                addAnnotatedClass(TelegramAdminContentUser.class).
                addAnnotatedClass(TelegramAdminSuperUser.class).
                addAnnotatedClass(TelegramClientUser.class).
                addAnnotatedClass(WebItem.class).
                addAnnotatedClass(Web2Item.class).
                addAnnotatedClass(Web3CryptoItem.class).
                addAnnotatedClass(Web2GameCode.class).
                addAnnotatedClass(Web2GiftCard.class).
                addAnnotatedClass(Web3NFT.class).
                addAnnotatedClass(Order.class).
                addAnnotatedClass(Payment.class).
                addAnnotatedClass(CryptoPayment.class).
                addAnnotatedClass(CreditCardPayment.class).
                addAnnotatedClass(ManageAdminContentOrder.class).
                addAnnotatedClass(CryptoPaymentBalance.class);
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();
        sessionFactory = configObj.buildSessionFactory(serviceRegistryObj);
        return sessionFactory;
    }

    public static Session spawnSession(){
        session = buildSessionFactory().openSession();
        return session;
    }

}