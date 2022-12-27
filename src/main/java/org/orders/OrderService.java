package org.orders;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.main.HibernateCommitsSpawner;
import org.main.HibernateSessionFactorySpawner;
import org.payments.CryptoPayment;
import org.payments.PaymentOrderService;
import org.users.TelegramClientUser;
import org.webitems.Web2Item;
import org.webitems.Web3CryptoItem;
import org.webitems.WebItem;
import org.webitems.WebItemService;

import java.util.List;

public abstract class OrderService {

    public static String createOrder(WebItem webItem, TelegramClientUser telegramClientUser){
        if(!telegramClientUser.isBanStatus()){
            Order order = new Order(telegramClientUser, webItem);
            HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
            spawner.createCommit(order);
            System.out.println("Order for client: " + telegramClientUser.getDisplayName() + " " + telegramClientUser.getIdTelegramUser() + " created");
            return "Order for client: " + telegramClientUser.getDisplayName() + " " + telegramClientUser.getIdTelegramUser() + " created";
        }
        System.out.println("User " + telegramClientUser.getDisplayName() + " can't create order. REASON: Banned");
        return "User " + telegramClientUser.getDisplayName() + " can't create order. REASON: Banned";
    }

    public static String updateOrder(Order order){
        HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
        spawner.updateCommit(order);
        System.out.println("Order updated");
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

    public static String cancelOrder(Order order){
        order.setState("canceled");
        PaymentOrderService.cancelPayment(order);
        updateOrder(order);
        return "Order canceled.";
    }


    public static String sendOrder(Order order){
        if (!order.getState().equals("canceled")){
            if (order.getPayment() != null && order.getPayment().isCompleted()) {
                if (order.getWebItem() instanceof Web2Item){
                    order.setState("completed");
                    order.getWebItem().setQuantity(order.getWebItem().getQuantity() -1);
                    WebItemService.updateWebItem(order.getWebItem());
                    updateOrder(order);
                    OrderEmailSenderService.sendMailTo(order.getEmailClient(), ((Web2Item) order.getWebItem()).getRedeemCode());
                    System.out.println("Your code: " + ((Web2Item) order.getWebItem()).getRedeemCode());
                    return "Your code: " + ((Web2Item) order.getWebItem()).getRedeemCode();
                }
                if (order.getWebItem() instanceof Web3CryptoItem){
                    System.out.println("Your NFT sent to: " + order.getTelegramClientUser().getCyptoWalletAdress());
                    order.setState("completed");
                    order.getWebItem().setQuantity(order.getWebItem().getQuantity() -1);
                    WebItemService.updateWebItem(order.getWebItem());
                    updateOrder(order);
                    System.out.println("Your NFT sent to: " + order.getTelegramClientUser().getCyptoWalletAdress());
                    return "Your NFT sent to: " + order.getTelegramClientUser().getCyptoWalletAdress();
                }

            }
            System.out.println("Order doesn't send due to uncompleted payment");
            return "Order doesn't send due to uncompleted payment";
        }
        System.out.println("Order doesn't send due to canceled order");
        return "Order doesn't send due to canceled order";
    }
}
