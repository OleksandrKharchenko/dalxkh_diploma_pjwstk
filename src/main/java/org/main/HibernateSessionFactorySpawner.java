package org.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.users.*;
import org.webitems.*;

public class HibernateSessionFactorySpawner {

    public static Session spawnSession() {
        SessionFactory factory = new Configuration().configure(EnvVars.HibirnateConfigFile).
                addAnnotatedClass(User.class).
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
                addAnnotatedClass(Web3NFT.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        return session;
    }

    public static void spawnInitSession() {
        SessionFactory factory = new Configuration().configure(EnvVars.HibirnateConfigFile).
                addAnnotatedClass(User.class).
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
                addAnnotatedClass(Web3NFT.class)
                .buildSessionFactory();
        Session session = factory.openSession();
    }

}
