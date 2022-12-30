package org.users;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.main.HibernateCommitsSpawner;
import org.main.HibernateSessionFactorySpawner;

public class TelegramClientUserService {

    public static void addTelegramClientUser(int idTelegramUser, String displayName){
        TelegramClientUser clientUser = new TelegramClientUser(idTelegramUser, displayName);
        HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
        spawner.createCommit(clientUser);
    }
    public static void addTelegramClientUser(TelegramClientUser clientUser){
        HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
        spawner.createCommit(clientUser);
    }

    public static void updateTelegramClientUser(TelegramClientUser clientUser){
        HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
        spawner.updateCommit(clientUser);
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

    public static boolean verifyIfExists(int idTelegramUser){
        Session session = HibernateSessionFactorySpawner.spawnSession();
        Query query;
        session.beginTransaction();
        try {
            query = session.createQuery("from User where idTelegramUser= :idTelegramUser", TelegramClientUser.class).setParameter("idTelegramUser", idTelegramUser);
            TelegramClientUser telegramClientUser = (TelegramClientUser) query.getSingleResult();
            session.close();
            return true;
        } catch (NoResultException noResultException){
            session.close();
            return false;
        }
    }
}
