package org.users;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.main.HibernateSessionFactorySpawner;

public class TelegramClientUserController {

    public static void addTelegramClientUser(String email, int idTelegramUser, String displayName){
        Session startUserSession = HibernateSessionFactorySpawner.spawnSession();
        TelegramClientUser clientUser = new TelegramClientUser(email, idTelegramUser, displayName);
        startUserSession.beginTransaction();
        startUserSession.persist(clientUser);
        startUserSession.getTransaction().commit();
    }
    public static void addTelegramClientUser(TelegramClientUser clientUser){
        Session startUserSession = HibernateSessionFactorySpawner.spawnSession();
        startUserSession.beginTransaction();
        startUserSession.persist(clientUser);
        startUserSession.getTransaction().commit();
    }

    public static TelegramClientUser getTelegramClientUser(int idTelegramUser){
        Session startSuperUserSession = HibernateSessionFactorySpawner.spawnSession();
        Query query;
        startSuperUserSession.beginTransaction();
        query = startSuperUserSession.createQuery("from User where idTelegramUser= :idTelegramUser").setParameter("idTelegramUser", idTelegramUser);
        TelegramClientUser telegramClientUser = (TelegramClientUser) query.getSingleResult();
        startSuperUserSession.close();
        return telegramClientUser;
    }
}
