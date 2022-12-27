package org.users;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.main.HibernateCommitsSpawner;
import org.main.HibernateSessionFactorySpawner;

public class TelegramClientUserService {

    public static void addTelegramClientUser(String email, int idTelegramUser, String displayName){
        TelegramClientUser clientUser = new TelegramClientUser(email, idTelegramUser, displayName);
        HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
        spawner.createCommit(clientUser);
    }
    public static void addTelegramClientUser(TelegramClientUser clientUser){
        HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
        spawner.createCommit(clientUser);
    }

    public static TelegramClientUser getTelegramClientUser(int idTelegramUser){
        Session startSuperUserSession = HibernateSessionFactorySpawner.spawnSession();
        Query query;
        startSuperUserSession.beginTransaction();
        query = startSuperUserSession.createQuery("from User where idTelegramUser= :idTelegramUser", TelegramClientUser.class).setParameter("idTelegramUser", idTelegramUser);
        TelegramClientUser telegramClientUser = (TelegramClientUser) query.getSingleResult();
        startSuperUserSession.close();
        return telegramClientUser;
    }
}
