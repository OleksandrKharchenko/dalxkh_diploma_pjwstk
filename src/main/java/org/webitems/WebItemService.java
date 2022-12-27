package org.webitems;

import org.hibernate.Session;
import org.main.HibernateCommitsSpawner;
import org.main.HibernateSessionFactorySpawner;
import org.users.TelegramAdminContentUser;

public abstract class WebItemService {

    public static void updateWebItem(WebItem webItem) {
        HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
        spawner.updateCommit(webItem);
        }

    public static void deleteWebItem(WebItem webItem) {
        HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
        spawner.deleteCommit(webItem);
    }

}
