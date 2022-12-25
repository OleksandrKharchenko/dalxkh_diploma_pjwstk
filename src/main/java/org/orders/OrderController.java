package org.orders;

import org.hibernate.Session;
import org.main.HibernateSessionFactorySpawner;
import org.users.TelegramAdminSuperUser;
import org.users.TelegramClientUser;
import org.webitems.WebItem;

public abstract class OrderController {

    public static void createOrder(WebItem webItem, TelegramClientUser telegramClientUser){
        Order order = new Order(telegramClientUser, webItem);
        Session startCreateOrderSession = HibernateSessionFactorySpawner.spawnSession();
        startCreateOrderSession.beginTransaction();
        startCreateOrderSession.persist(order);
        startCreateOrderSession.getTransaction().commit();
    }
}
