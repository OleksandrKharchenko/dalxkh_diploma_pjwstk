package org.users;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.main.HibernateSessionFactorySpawner;
import org.webitems.*;

import java.util.List;

public abstract class TelegramAdminContentUserController {

    public static List<WebItem> getWebItems(String typeOf) {
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

    public static WebItem getWebItems(int idItem) {
        Session startGetWebItemsSession = HibernateSessionFactorySpawner.spawnSession();
        Query query;
        startGetWebItemsSession.beginTransaction();
        query = startGetWebItemsSession.createQuery("from WebItem where idItem= :idItem", WebItem.class).setParameter("idItem", idItem);
        WebItem webItem = (WebItem) query.getSingleResult();
        startGetWebItemsSession.close();
        return webItem;
    }

    public static String deleteWebItem(WebItem webItem, TelegramAdminContentUser contentOperUser){
        if (contentOperUser.isOperational()) {
            Session deleteWeb2ItemSession = HibernateSessionFactorySpawner.spawnSession();
            deleteWeb2ItemSession.beginTransaction();
            deleteWeb2ItemSession.remove(webItem);
            deleteWeb2ItemSession.getTransaction().commit();
            return webItem.getName() + " deleted.";
        }
        return "ERROR: on delete item, content admin user " + contentOperUser.getIdTelegramUser() + " is disabled";
    }

    public static String addWebItem(WebItem webItem, TelegramAdminContentUser contentOperUser){
        if (contentOperUser.isOperational()) {
            Session addWeb2ItemSession = HibernateSessionFactorySpawner.spawnSession();
            webItem.setAddedBy(contentOperUser.getIdTelegramUser());
            addWeb2ItemSession.beginTransaction();
            addWeb2ItemSession.persist(webItem);
            addWeb2ItemSession.getTransaction().commit();
            return webItem.getName() + " added.";
        }
        return "ERROR: on add item, content admin user " + contentOperUser.getIdTelegramUser() + " is disabled";
    }

    public static String editWebItem(WebItem webItem, TelegramAdminContentUser contentOperUser) {
        if (contentOperUser.isOperational()) {
            Session editWeb2ItemSession = HibernateSessionFactorySpawner.spawnSession();
            editWeb2ItemSession.beginTransaction();
            editWeb2ItemSession.merge(webItem);
            editWeb2ItemSession.getTransaction().commit();
            return webItem.getName() + " updated.";
        }
        return "ERROR: on add item, content admin user " + contentOperUser.getIdTelegramUser() + " is disabled";
    }

    public static String getAllOrders() {

        return "---.";
    }
}
