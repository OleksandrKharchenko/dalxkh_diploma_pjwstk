package org.users;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.main.HibernateCommitsSpawner;
import org.main.HibernateSessionFactorySpawner;
import org.orders.Order;
import org.orders.OrderService;
import org.webitems.*;

import java.util.ArrayList;
import java.util.List;

public class TelegramAdminContentUserService {

    public List<WebItem> getWebItems(String typeOf) {
        Session startGetWebItemsSession = HibernateSessionFactorySpawner.spawnSession();
        Query query = null;
        List<WebItem> listWebItems = null;
        startGetWebItemsSession.beginTransaction();
        switch (typeOf){
            case "Web3NFT":
                query = startGetWebItemsSession.createQuery("from WebItem w where TYPE(w) = Web3NFT", Web3NFT.class);
                listWebItems = query.getResultList();
                startGetWebItemsSession.close();
                return listWebItems;
            case "Web2GameCode":
                query = startGetWebItemsSession.createQuery("from WebItem w where TYPE(w) = Web2GameCode", Web2GameCode.class);
                listWebItems = query.getResultList();
                startGetWebItemsSession.close();
                return listWebItems;
            case "Web2GiftCard":
                query = startGetWebItemsSession.createQuery("from WebItem w where TYPE(w) = Web2GiftCard", Web2GiftCard.class);
                listWebItems = query.getResultList();
                startGetWebItemsSession.close();
                return listWebItems;
            default:
                throw new IllegalArgumentException("Invalid type: " + typeOf);
        }
    }

    public String deleteWebItem(WebItem webItem, TelegramAdminContentUser contentOperUser){
        if (contentOperUser.isOperational()) {
            HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
            spawner.deleteCommit(webItem);
            return webItem.getName() + " deleted.";
        }
        return "ERROR: on delete item, content admin user " + contentOperUser.getIdTelegramUser() + " is disabled";
    }

    public String addWebItem(WebItem webItem, TelegramAdminContentUser contentOperUser){
        if (contentOperUser.isOperational()) {
            webItem.setAddedBy(contentOperUser.getIdTelegramUser());
            HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
            spawner.createCommit(webItem);
            return webItem.getName() + " added.";
        }
        return "ERROR: on add item, content admin user " + contentOperUser.getIdTelegramUser() + " is disabled";
    }

    public String editWebItem(WebItem webItem, TelegramAdminContentUser contentOperUser) {
        if (contentOperUser.isOperational()) {
            HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
            spawner.updateCommit(webItem);
            return webItem.getName() + " updated.";
        }
        return "ERROR: on add item, content admin user " + contentOperUser.getIdTelegramUser() + " is disabled";
    }

    public List<Order> getOrders(){
        Session startSuperUserSession = HibernateSessionFactorySpawner.spawnSession();
        Query query;
        startSuperUserSession.beginTransaction();
        query = startSuperUserSession.createQuery("from Order", Order.class);
        List<Order> orderList = query.getResultList();
        startSuperUserSession.close();
        return orderList;
    }

    public String deleteOrder(int idOrder) {
        OrderService orderService = new OrderService();
        Order order = orderService.getOrders(idOrder);
        HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
        spawner.deleteCommit(order);
        return "Order: <b>" + order.getIdOrder() + "</b> removed.";
    }


}
