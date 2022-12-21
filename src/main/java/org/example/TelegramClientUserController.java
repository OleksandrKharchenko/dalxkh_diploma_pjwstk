package org.example;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class TelegramClientUserController {

    public static void addTelegramClientUser(String email, int idTelegramUser, String displayName){
        Session startUserSession = HibernateSessionFactorySpawner.spawnSession();
        TelegramClientUser clientUser = new TelegramClientUser(email, idTelegramUser, displayName);
        startUserSession.beginTransaction();
        startUserSession.persist(clientUser);
        startUserSession.getTransaction().commit();
        startUserSession.flush();
        startUserSession.close();
    }
    public static void addTelegramClientUser(TelegramClientUser clientUser){
        Session startUserSession = HibernateSessionFactorySpawner.spawnSession();
        startUserSession.beginTransaction();
        startUserSession.persist(clientUser);
        startUserSession.getTransaction().commit();
        startUserSession.flush();
        startUserSession.close();
    }

    public static TelegramClientUser getIClientUser(int idTelegramUser){
        Session startSuperUserSession = HibernateSessionFactorySpawner.spawnSession();
        Query query;
        startSuperUserSession.beginTransaction();
        query = startSuperUserSession.createQuery("from User where idTelegramUser= " + idTelegramUser);
        TelegramClientUser telegramClientUser = (TelegramClientUser) query.uniqueResult();
        return telegramClientUser;
    }
}
