package org.webitems;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.main.HibernateCommitsSpawner;
import org.main.HibernateSessionFactorySpawner;
import org.users.TelegramAdminContentUser;

import java.util.List;

public class WebItemService {

    public void updateWebItem(WebItem webItem) {
        HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
        spawner.updateCommit(webItem);
        }

    public void deleteWebItem(WebItem webItem) {
        HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
        spawner.deleteCommit(webItem);
    }

    public WebItem getWebItem(int idItem) {
        Session startGetWebItemSession = HibernateSessionFactorySpawner.spawnSession();
        Query query;
        startGetWebItemSession.beginTransaction();
        query = startGetWebItemSession.createQuery("from WebItem where idItem= :idItem", WebItem.class).setParameter("idItem", idItem);
        WebItem webItem = (WebItem) query.getSingleResult();
        startGetWebItemSession.close();
        return webItem;
    }

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
    public boolean isAvailable(int idItem) {
        Session startGetWebItemSession = HibernateSessionFactorySpawner.spawnSession();
        Query query;
        startGetWebItemSession.beginTransaction();
        query = startGetWebItemSession.createQuery("from WebItem where idItem= :idItem", WebItem.class).setParameter("idItem", idItem);
        WebItem webItem = (WebItem) query.getSingleResult();
        if (webItem.getQuantity() == 0) {
            return false;
        }
        return true;
    }
}
