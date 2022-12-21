package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactorySpawner {

    public static Session spawnSession() {
        SessionFactory factory = new Configuration().configure(EnvVars.HibirnateConfigFile).
                addAnnotatedClass(User.class).
                addAnnotatedClass(TelegramUser.class).
                addAnnotatedClass(TelegramOperationalUser.class).
                addAnnotatedClass(TelegramAdminContentUser.class).
                addAnnotatedClass(TelegramAdminSuperUser.class).
                addAnnotatedClass(TelegramClientUser.class)
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
                addAnnotatedClass(TelegramClientUser.class)
                .buildSessionFactory();
        Session session = factory.openSession();
        session.close();
    }

}
