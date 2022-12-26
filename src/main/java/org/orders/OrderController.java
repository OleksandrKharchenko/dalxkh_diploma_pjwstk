package org.orders;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.main.HibernateSessionFactorySpawner;
import org.users.TelegramAdminSuperUser;
import org.users.TelegramClientUser;
import org.webitems.Web2GameCode;
import org.webitems.Web2GiftCard;
import org.webitems.Web3NFT;
import org.webitems.WebItem;

import java.util.List;

public abstract class OrderController {

    public static String createOrder(WebItem webItem, TelegramClientUser telegramClientUser){
        if(!telegramClientUser.isBanStatus()){
            Order order = new Order(telegramClientUser, webItem);
            Session startCreateOrderSession = HibernateSessionFactorySpawner.spawnSession();
            startCreateOrderSession.beginTransaction();
            startCreateOrderSession.persist(order);
            startCreateOrderSession.getTransaction().commit();
            return "Order for client: " + telegramClientUser.getDisplayName() + " " + telegramClientUser.getIdTelegramUser() + " created";
        }
        return "User " + telegramClientUser.getDisplayName() + " can't create order. REASON: Banned";
    }

    public static String updateOrder(Order order){
            Session startCreateOrderSession = HibernateSessionFactorySpawner.spawnSession();
            startCreateOrderSession.beginTransaction();
            startCreateOrderSession.merge(order);
            startCreateOrderSession.getTransaction().commit();
            return "Order updated";
    }

    public static Order getOrders(int idOrder) {
        Session startGetOrdersSession = HibernateSessionFactorySpawner.spawnSession();
        Query query;
        startGetOrdersSession.beginTransaction();
        query = startGetOrdersSession.createQuery("from Order where idOrder= :idOrder", Order.class).setParameter("idOrder", idOrder);
        Order order = (Order) query.getSingleResult();
        startGetOrdersSession.close();
        return order;
    }

    public static List<Order> getOrders() {
        Session startGetOrdersSession = HibernateSessionFactorySpawner.spawnSession();
        Query query = null;
        List<Order> listOrders = null;
        startGetOrdersSession.beginTransaction();
        query = startGetOrdersSession.createQuery("from Order", Order.class);
        listOrders = query.getResultList();
        startGetOrdersSession.close();
        return listOrders;
    }
}
