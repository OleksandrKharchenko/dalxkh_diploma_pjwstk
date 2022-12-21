package org.example;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Objects;

public abstract class TelegramAdminSuperUserController {

    public static String addOperationalUser(TelegramUser telegramUser, TelegramAdminSuperUser sessionSuperUser, String accessType) {

        Session addOperUserSession = HibernateSessionFactorySpawner.spawnSession();
        System.out.println(sessionSuperUser.getAccessSuperKey());
        if (sessionSuperUser.getAccessSuperKey().equals("9x09admin")){
            if (accessType == "content") {
                TelegramAdminContentUser contentUser = new TelegramAdminContentUser(telegramUser.getEmail(), telegramUser.getIdTelegramUser(), telegramUser.getDisplayName());
                contentUser.setAccessType("content");
                contentUser.setOperational(true);
                contentUser.setAccessAddedBy(sessionSuperUser.getIdTelegramUser());
                addOperUserSession.beginTransaction();
                addOperUserSession.remove(telegramUser);
                addOperUserSession.persist(contentUser);
                addOperUserSession.getTransaction().commit();
                addOperUserSession.flush();
                addOperUserSession.close();
                return "Admin Content User created from:" + " Telegram id " + telegramUser.getIdTelegramUser() + " Telegram Display Name " + telegramUser.getDisplayName();
            }
            if (accessType == "super") {
                String accessSuperKey = new String("9x09admin");
                TelegramAdminSuperUser superUser = new TelegramAdminSuperUser(telegramUser.getEmail(), telegramUser.getIdTelegramUser(), telegramUser.getDisplayName(), accessSuperKey);
                superUser.setAccessType("super");
                superUser.setOperational(true);
                superUser.setAccessAddedBy(sessionSuperUser.getIdTelegramUser());
                addOperUserSession.beginTransaction();
                addOperUserSession.remove(telegramUser);
                addOperUserSession.persist(superUser);
                addOperUserSession.getTransaction().commit();
                addOperUserSession.flush();
                addOperUserSession.close();
                return "Admin Super User created from:" + " Telegram id " + telegramUser.getIdTelegramUser() + " Telegram Display Name " + telegramUser.getDisplayName();
            }
            else {
                return "Wrong access key provided.";
            }
        }
        return "You don't have permission to add Operational User. Wrong Access Super Key.";
    }



    public static String disableOperationalUser(TelegramOperationalUser operationalUser, TelegramAdminSuperUser sessionSuperUser) {
        if (operationalUser.isOperational()) {
            operationalUser.setOperational(false);
            operationalUser.setAccessType("none");
            operationalUser.setAccessDisabledBy(sessionSuperUser.getIdTelegramUser());
            return "Operational user disabled.";
            }
        else {
            return "User is not operational.";
        }
    }


    public static TelegramAdminSuperUser initiateTelegramFirstSuperUser(String email, int idTelegramUser, String displayName){
        Session startSuperUserSession = HibernateSessionFactorySpawner.spawnSession();
        TelegramAdminSuperUser superUser = new TelegramAdminSuperUser(email, idTelegramUser, displayName, "9x09admin");
        superUser.setAccessType("initial");
        startSuperUserSession.beginTransaction();
        startSuperUserSession.persist(superUser);
        startSuperUserSession.getTransaction().commit();
        startSuperUserSession.flush();
        startSuperUserSession.close();
        return superUser;
    }
    public static void initiateTelegramFirstSuperUser(TelegramAdminSuperUser superUser){
        Session startSuperUserSession = HibernateSessionFactorySpawner.spawnSession();
        superUser.setAccessType("initial");
        startSuperUserSession.beginTransaction();
        startSuperUserSession.persist(superUser);
        startSuperUserSession.getTransaction().commit();
        startSuperUserSession.flush();
        startSuperUserSession.close();
    }

    public static TelegramAdminSuperUser getInitialSuperUser(){
        Session startSuperUserSession = HibernateSessionFactorySpawner.spawnSession();
        Query query;
        startSuperUserSession.beginTransaction();
        query = startSuperUserSession.createQuery("from User where accessType= 'initial'");
        TelegramAdminSuperUser initialSuperUser = (TelegramAdminSuperUser) query.uniqueResult();
        return initialSuperUser;
    }
}
