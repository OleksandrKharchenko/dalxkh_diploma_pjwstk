package org.users;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.main.HibernateSessionFactorySpawner;

public abstract class TelegramAdminSuperUserController {

    public static String addOperationalUser(TelegramUser telegramUser, TelegramAdminSuperUser sessionSuperUser, String accessType) {

        if (sessionSuperUser.getAccessSuperKey().equals("9x09admin")){
            if (accessType == "content") {
                Session addOperUserSessionRem = HibernateSessionFactorySpawner.spawnSession();
                TelegramAdminContentUser contentUser = new TelegramAdminContentUser(telegramUser.getEmail(), telegramUser.getIdTelegramUser(), telegramUser.getDisplayName());
                contentUser.setAccessType("content");
                contentUser.setOperational(true);
                contentUser.setAccessAddedBy(sessionSuperUser.getIdTelegramUser());
                addOperUserSessionRem.beginTransaction();
                addOperUserSessionRem.remove(telegramUser);
                addOperUserSessionRem.getTransaction().commit();
                Session addOperUserSessionPersist = HibernateSessionFactorySpawner.spawnSession();
                addOperUserSessionPersist.beginTransaction();
                addOperUserSessionPersist.persist(contentUser);
                addOperUserSessionPersist.getTransaction().commit();
                return "Admin Content User created from:" + " Telegram id " + telegramUser.getIdTelegramUser() + " Telegram Display Name " + telegramUser.getDisplayName();
            }
            if (accessType == "super") {
                String accessSuperKey = new String("9x09admin");
                Session addOperUserSessionRem = HibernateSessionFactorySpawner.spawnSession();
                TelegramAdminContentUser contentUser = new TelegramAdminContentUser(telegramUser.getEmail(), telegramUser.getIdTelegramUser(), telegramUser.getDisplayName());
                contentUser.setAccessType("super");
                contentUser.setOperational(true);
                contentUser.setAccessAddedBy(sessionSuperUser.getIdTelegramUser());
                addOperUserSessionRem.beginTransaction();
                addOperUserSessionRem.remove(telegramUser);
                addOperUserSessionRem.getTransaction().commit();
                Session addOperUserSessionPersist = HibernateSessionFactorySpawner.spawnSession();
                addOperUserSessionPersist.beginTransaction();
                addOperUserSessionPersist.persist(contentUser);
                addOperUserSessionPersist.getTransaction().commit();
                return "Admin Super User created from:" + " Telegram id " + telegramUser.getIdTelegramUser() + " Telegram Display Name " + telegramUser.getDisplayName();
            }
            else {
                return "Wrong access key provided.";
            }
        }
        return "You don't have permission to add Operational User. Wrong Access Super Key.";
    }



    public static String disableOperationalUser(TelegramOperationalUser operationalUser, TelegramAdminSuperUser sessionSuperUser) {
        if (sessionSuperUser.getAccessSuperKey().equals("9x09admin")) {
            if (operationalUser.isOperational()) {
                Session disableOperUserSession = HibernateSessionFactorySpawner.spawnSession();
                operationalUser.setOperational(false);
                operationalUser.setAccessType("none");
                operationalUser.setAccessDisabledBy(sessionSuperUser.getIdTelegramUser());
                disableOperUserSession.beginTransaction();
                disableOperUserSession.saveOrUpdate(operationalUser);
                disableOperUserSession.getTransaction().commit();
                return "Operational user disabled.";
            } else {
                return "Operational user already disabled.";
            }
        }
        return "You don't have permission to disable Operational User. Wrong Access Super Key.";
    }

    public static String banTelegramClient(TelegramClientUser telegramClientUser, TelegramAdminSuperUser sessionSuperUser){
        if (sessionSuperUser.getAccessSuperKey().equals("9x09admin")) {
            if (!telegramClientUser.isBanStatus()) {
                Session banClientUserSession = HibernateSessionFactorySpawner.spawnSession();
                telegramClientUser.setBanStatus(true);
                telegramClientUser.setBannedBy(sessionSuperUser.getIdTelegramUser());
                banClientUserSession.beginTransaction();
                banClientUserSession.saveOrUpdate(telegramClientUser);
                banClientUserSession.getTransaction().commit();
                return "Client user banned.";
            } else {
                return "Client user already banned.";
            }
        }
        return "You don't have permission to disable Operational User. Wrong Access Super Key.";
    }

    public static TelegramAdminSuperUser initiateTelegramFirstSuperUser(String email, int idTelegramUser, String displayName){
        Session startSuperUserSession = HibernateSessionFactorySpawner.spawnSession();
        TelegramAdminSuperUser superUser = new TelegramAdminSuperUser(email, idTelegramUser, displayName, "9x09admin");
        superUser.setAccessType("initial");
        startSuperUserSession.beginTransaction();
        startSuperUserSession.persist(superUser);
        startSuperUserSession.getTransaction().commit();
        return superUser;
    }
    public static void initiateTelegramFirstSuperUser(TelegramAdminSuperUser superUser){
        Session startSuperUserSession = HibernateSessionFactorySpawner.spawnSession();
        superUser.setAccessType("initial");
        startSuperUserSession.beginTransaction();
        startSuperUserSession.persist(superUser);
        startSuperUserSession.getTransaction().commit();
    }

    public static TelegramAdminSuperUser getInitialSuperUser(){
        Session startSuperUserSession = HibernateSessionFactorySpawner.spawnSession();
        String initial = "initial";
        Query query;
        startSuperUserSession.beginTransaction();
        query = startSuperUserSession.createQuery("from User where accessType= :initial", TelegramAdminSuperUser.class).setParameter("initial", initial);
        TelegramAdminSuperUser initialSuperUser = (TelegramAdminSuperUser) query.getSingleResult();;
        return initialSuperUser;
    }

    public static TelegramOperationalUser getTelegramOperUser(int idTelegramOperUser){
        Session startSuperUserSession = HibernateSessionFactorySpawner.spawnSession();
        Query query;
        startSuperUserSession.beginTransaction();
        query = startSuperUserSession.createQuery("from User where idTelegramUser= :idTelegramUser and isOperational= :isOperational", TelegramOperationalUser.class)
                .setParameter("isOperational", true)
                .setParameter("idTelegramUser", idTelegramOperUser);
        TelegramOperationalUser telegramOperUser = (TelegramOperationalUser) query.getSingleResult();;
        return telegramOperUser;
    }



}
