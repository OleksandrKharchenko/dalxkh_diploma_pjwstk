package org.users;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.main.HibernateCommitsSpawner;
import org.main.HibernateSessionFactorySpawner;
import org.orders.Order;
import org.orders.OrderService;
import org.webitems.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TelegramAdminContentUserService {

    public List<WebItem> getWebItemsByType(String typeOf) {
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

    public List<Order> getOrders(){
        Session startSuperUserSession = HibernateSessionFactorySpawner.spawnSession();
        Query query;
        startSuperUserSession.beginTransaction();
        query = startSuperUserSession.createQuery("from Order", Order.class);
        List<Order> orderList = query.getResultList();
        startSuperUserSession.close();
        return orderList;
    }

    public List<WebItem> getWebItems(){
        Session startSuperUserSession = HibernateSessionFactorySpawner.spawnSession();
        Query query;
        startSuperUserSession.beginTransaction();
        query = startSuperUserSession.createQuery("from WebItem", WebItem.class);
        List<WebItem> webItemList = query.getResultList();
        startSuperUserSession.close();
        return webItemList;
    }

    public String deleteOrder(int idOrder) {
        OrderService orderService = new OrderService();
        Order order = orderService.getOrders(idOrder);
        orderService.deleteOrder(order);
        return "Order: <b>" + order.getIdOrder() + "</b> removed.";
    }

    public String deleteWebItem(int idWebItem) {
        WebItemService webItemService = new WebItemService();
        WebItem webItem = webItemService.getWebItem(idWebItem);
        webItemService.deleteWebItem(webItem);
        return "WebItem: <b>" + webItem.getIdItem() + "</b> removed.";
    }

    public String addWebItem(WebItem webItem) {
        try {
            WebItemService webItemService = new WebItemService();
            webItemService.addWebItem(webItem);
            return "WebItem: <b>" + webItem.getIdItem() + "</b> added.";
        } catch (Exception e) {
            return "WebItem is not added, something went wrong.";
        }

    }

}
