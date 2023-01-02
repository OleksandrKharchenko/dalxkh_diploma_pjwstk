package org.orders;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.main.HibernateCommitsSpawner;
import org.main.HibernateSessionFactorySpawner;
import org.payments.CryptoPayment;
import org.payments.PaymentOrderService;
import org.users.TelegramClientUser;
import org.webitems.*;

import java.util.List;

public abstract class OrderService {

    public static Order createOrder(WebItem webItem, TelegramClientUser telegramClientUser){
        if(!telegramClientUser.isBanStatus()){
            Order order = new Order(telegramClientUser, webItem);
            HibernateCommitsSpawner spawner = new HibernateCommitsSpawner();
            spawner.createCommit(order);
            return order;
        }
        return null;
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
        if (!order.getState().equals("canceled") && !order.getState().equals("completed")){
            if (order.getPayment() != null && order.getPayment().isCompleted()) {
                if (order.getWebItem() instanceof Web2Item){
                    WebItemService webItemService = new WebItemService();
                    order.setState("completed");
                    order.getWebItem().setQuantity(order.getWebItem().getQuantity() -1);
                    webItemService.updateWebItem(order.getWebItem());
                    updateOrder(order);
                    OrderEmailSenderService.sendMailTo(order.getEmailClient(), ((Web2Item) order.getWebItem()).getRedeemCode());
                    System.out.println("Your code: " + ((Web2Item) order.getWebItem()).getRedeemCode());
                    return "Your code: " + ((Web2Item) order.getWebItem()).getRedeemCode();
                }
                if (order.getWebItem() instanceof Web3CryptoItem){
                    OrderCryptoReceiverSenderService orderCryptoReceiverSenderService = new OrderCryptoReceiverSenderService();
                    try {
                        WebItemService webItemService = new WebItemService();
                        String txHash = orderCryptoReceiverSenderService.sendCryptoItem(order.getTelegramClientUser().getCyptoWalletAdress());
                        order.setCryptoTxHashNFT(txHash);
                        order.setState("completed");
                        order.getWebItem().setQuantity(order.getWebItem().getQuantity() -1);
                        webItemService.updateWebItem(order.getWebItem());
                        updateOrder(order);
                        return "Your NFT sent to: " + order.getTelegramClientUser().getCyptoWalletAdress() + "\nTxHash: " + order.getCryptoTxHashNFT();
                    } catch (Exception e) {
                    }
                }

            }
            System.out.println("Order doesn't send due to uncompleted payment.");
            return "Order doesn't send due to uncompleted payment.";
        }
        System.out.println("Order doesn't send due to canceled or completed order.");
        return "Order doesn't send due to canceled or completed order.";
    }
}
