package org.users;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.main.HibernateCommitsSpawner;
import org.main.HibernateSessionFactorySpawner;

import java.util.ArrayList;
import java.util.List;

public class TelegramAdminSuperUserService {

    public String addOperationalUser(TelegramClientUser telegramUser, TelegramAdminSuperUser sessionSuperUser, String accessType) {

        if (sessionSuperUser.getAccessSuperKey().equals("9x09admin")){
            if (accessType == "content") {
                TelegramAdminContentUser contentUser = new TelegramAdminContentUser(telegramUser.getEmail(), telegramUser.getIdTelegramUser(), telegramUser.getDisplayName());
                contentUser.setAccessType("content");
                contentUser.setOperational(true);
                contentUser.setAccessAddedBy(sessionSuperUser.getIdTelegramUser());
                HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
                spawner.deleteCommit(telegramUser);
                spawner.createCommit(contentUser);
                return "Admin Content User created from:" + " <b>" + telegramUser.getIdTelegramUser() + "</b> Name: <b>" + telegramUser.getDisplayName() + "</b>";
            }
            if (accessType == "super") {
                String accessSuperKey = new String("9x09admin");
                TelegramAdminSuperUser superUser = new TelegramAdminSuperUser(telegramUser.getEmail(), telegramUser.getIdTelegramUser(), telegramUser.getDisplayName(), accessSuperKey);
                superUser.setAccessType("super");
                superUser.setOperational(true);
                superUser.setAccessAddedBy(sessionSuperUser.getIdTelegramUser());
                HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
                spawner.deleteCommit(telegramUser);
                spawner.createCommit(superUser);
                return "Admin Super User created from:" + " Telegram id " + telegramUser.getIdTelegramUser() + " Telegram Display Name " + telegramUser.getDisplayName();
            }
            else {
                return "Wrong access key provided.";
            }
        }
        return "You don't have permission to add Operational User. Wrong Access Super Key.";
    }

    public String dissolveOperationalUser(TelegramOperationalUser telegramUser, TelegramAdminSuperUser sessionSuperUser) {
        if (sessionSuperUser.getAccessSuperKey().equals("9x09admin")){
                TelegramClientUser clientUser = new TelegramClientUser(telegramUser.getIdTelegramUser(), telegramUser.getDisplayName());
                HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
                spawner.deleteCommit(telegramUser);
                spawner.createCommit(clientUser);
                return "User: <b>" + telegramUser.getIdTelegramUser() + "</b> Name: <b>" + telegramUser.getDisplayName() + "</b> has been dissolved.";

        }
        return "You don't have permission to dissolve Operational User. Wrong Access Super Key.";
    }

    public String disableOperationalUser(TelegramOperationalUser operationalUser, TelegramAdminSuperUser sessionSuperUser) {
        if (sessionSuperUser.getAccessSuperKey().equals("9x09admin")) {
            if (operationalUser.isOperational()) {
                operationalUser.setOperational(false);
                operationalUser.setAccessType("none");
                operationalUser.setAccessDisabledBy(sessionSuperUser.getIdTelegramUser());
                HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
                spawner.updateCommit(operationalUser);
                return "Operational user disabled.";
            } else {
                return "Operational user already disabled.";
            }
        }
        return "You don't have permission to disable Operational User. Wrong Access Super Key.";
    }

    public String banTelegramClient(TelegramClientUser telegramClientUser, TelegramAdminSuperUser sessionSuperUser){
        if (sessionSuperUser.getAccessSuperKey().equals("9x09admin")) {
            if (!telegramClientUser.isBanStatus()) {
                telegramClientUser.setBanStatus(true);
                telegramClientUser.setBannedBy(sessionSuperUser.getIdTelegramUser());
                HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
                spawner.updateCommit(telegramClientUser);
                return "User: <b>" + telegramClientUser.getIdTelegramUser() + "</b> is banned.";
            } else {
                return "User: <b>" + telegramClientUser.getIdTelegramUser() + "</b> already banned.";
            }
        }
        return "You don't have permission. Wrong Access Super Key.";
    }

    public String unbanTelegramClient(TelegramClientUser telegramClientUser, TelegramAdminSuperUser sessionSuperUser){
        if (sessionSuperUser.getAccessSuperKey().equals("9x09admin")) {
            if (telegramClientUser.isBanStatus()) {
                telegramClientUser.setBanStatus(false);
                telegramClientUser.setBannedBy(0);
                HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
                spawner.updateCommit(telegramClientUser);
                return "User: <b>" + telegramClientUser.getIdTelegramUser() + "</b> is unbanned.";
            } else {
                return "User: <b>" + telegramClientUser.getIdTelegramUser() + "</b> is not banned.";
            }
        }
        return "You don't have permission. Wrong Access Super Key.";
    }

    public TelegramAdminSuperUser initiateTelegramFirstSuperUser(String email, long idTelegramUser, String displayName){
        TelegramAdminSuperUser superUser = new TelegramAdminSuperUser(email, idTelegramUser, displayName, "9x09admin");
        superUser.setAccessType("initial");
        superUser.setOperational(true);
        HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
        spawner.createCommit(superUser);
        return superUser;
    }

    public void initiateTelegramFirstSuperUser(TelegramAdminSuperUser superUser){
        superUser.setAccessType("initial");
        superUser.setOperational(true);
        HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
        spawner.createCommit(superUser);
    }

    public TelegramAdminSuperUser getInitialSuperUser(){
        Session startSuperUserSession = HibernateSessionFactorySpawner.spawnSession();
        String initial = "initial";
        Query query;
        startSuperUserSession.beginTransaction();
        query = startSuperUserSession.createQuery("from User where accessType= :initial", TelegramAdminSuperUser.class).setParameter("initial", initial);
        TelegramAdminSuperUser initialSuperUser = (TelegramAdminSuperUser) query.getSingleResult();
        startSuperUserSession.close();
        return initialSuperUser;
    }

    public TelegramOperationalUser getTelegramOperUser(long idTelegramOperUser){
        Session startSuperUserSession = HibernateSessionFactorySpawner.spawnSession();
        Query query;
        startSuperUserSession.beginTransaction();
        query = startSuperUserSession.createQuery("from User where idTelegramUser= :idTelegramUser and isOperational= :isOperational", TelegramOperationalUser.class)
                .setParameter("isOperational", true)
                .setParameter("idTelegramUser", idTelegramOperUser);
        TelegramOperationalUser telegramOperUser = (TelegramOperationalUser) query.getSingleResult();
        startSuperUserSession.close();
        return telegramOperUser;
    }

    public boolean isOperational(long idTelegramOperUser){
        Session startSuperUserSession = HibernateSessionFactorySpawner.spawnSession();
        Query query;
        startSuperUserSession.beginTransaction();
        query = startSuperUserSession.createQuery("from User where idTelegramUser= :idTelegramUser", TelegramUser.class)
                .setParameter("idTelegramUser", idTelegramOperUser);
        if (query.getSingleResult() instanceof TelegramOperationalUser){
            startSuperUserSession.close();
            return true;
        }
        startSuperUserSession.close();
        return false;
    }

    public boolean isSuperUser(long idTelegramOperUser){
        Session startSuperUserSession = HibernateSessionFactorySpawner.spawnSession();
        Query query;
        startSuperUserSession.beginTransaction();
        query = startSuperUserSession.createQuery("from User where idTelegramUser= :idTelegramUser", TelegramUser.class)
                .setParameter("idTelegramUser", idTelegramOperUser);
        if (query.getSingleResult() instanceof TelegramAdminSuperUser){
            startSuperUserSession.close();
            return true;
        }
        startSuperUserSession.close();
        return false;
    }

    public List<TelegramClientUser> getTelegramClientUsers(){
        Session startSuperUserSession = HibernateSessionFactorySpawner.spawnSession();
        Query query;
        startSuperUserSession.beginTransaction();
        query = startSuperUserSession.createQuery("from User u where TYPE(u) = TelegramClientUser", TelegramClientUser.class);
        List<TelegramClientUser> telegramClientUsers = (ArrayList<TelegramClientUser>) query.getResultList();
        startSuperUserSession.close();
        return telegramClientUsers;
    }

    public List<TelegramOperationalUser> getTelegramOperUsers(){
        Session startSuperUserSession = HibernateSessionFactorySpawner.spawnSession();
        Query query;
        startSuperUserSession.beginTransaction();
        query = startSuperUserSession.createQuery("from User u where TYPE(u) = TelegramAdminContentUser", TelegramAdminContentUser.class);
        List<TelegramOperationalUser> telegramOperUsers = (ArrayList<TelegramOperationalUser>) query.getResultList();
        startSuperUserSession.close();
        return telegramOperUsers;
    }

}
