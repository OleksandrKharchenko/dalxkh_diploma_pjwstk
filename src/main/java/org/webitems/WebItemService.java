package org.webitems;

import org.hibernate.Session;
import org.main.HibernateSessionFactorySpawner;
import org.users.TelegramAdminContentUser;

public abstract class WebItemService {

    public static void updateWebItem(WebItem webItem) {
        Session updateWebItemSession = HibernateSessionFactorySpawner.spawnSession();
        updateWebItemSession.beginTransaction();
        updateWebItemSession.merge(webItem);
        updateWebItemSession.getTransaction().commit();
        }

    public static void deleteWebItem(WebItem webItem) {
        Session updateWebItemSession = HibernateSessionFactorySpawner.spawnSession();
        updateWebItemSession.beginTransaction();
        updateWebItemSession.remove(webItem);
        updateWebItemSession.getTransaction().commit();
    }

}
