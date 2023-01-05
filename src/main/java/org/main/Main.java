package org.main;


import org.orders.*;
import org.payments.*;
import org.webitems.*;
import org.users.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String[] args) throws IOException {
//    OrderEmailSenderService.sendMailTo("deepalexkh@gmail.com", "dsadsad");

// *********************ENTITY GENERATE DB INITIATION**********************
//        HibernateSessionFactorySpawner.spawnSession();
//        CryptoPaymentBalance balance = null;
//        try {
//            balance = new CryptoPaymentBalance();
//            balance.createBalance();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        CryptoPaymentBalance balance = null;
//        try {
//            balance = new CryptoPaymentBalance();
//            balance.updateBalance();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
// ************************************************************************

// *********************SUPER USER INITIATION******************************
//        TelegramAdminSuperUserService telegramAdminSuperUserService = new TelegramAdminSuperUserService();
//        String admEmail = "deepalexkh@gmail.com";
//        int admIdTelegramUser = 512510188;
//        String admDisplayName = "Alex Kharchenko";
//        telegramAdminSuperUserService.initiateTelegramFirstSuperUser(admEmail, admIdTelegramUser, admDisplayName);
// ************************************************************************

// *********************ADDING USERS***************************************
//        TelegramClientUserService telegramClientUserService = new TelegramClientUserService();
//        TelegramClientUser user1 = new TelegramClientUser("test@test.com", 546210188, "Elon Musk");
//        telegramClientUserService.addTelegramClientUser(user1);
//        TelegramClientUser user2 = new TelegramClientUser("test2@test.com", 546240188, "Elon Tusk");
//        telegramClientUserService.addTelegramClientUser(user2);
//        TelegramClientUser user3 = new TelegramClientUser("test3@test.com", 546250188, "Elon Client");
//        telegramClientUserService.addTelegramClientUser(user3);
//        TelegramClientUser user4 = new TelegramClientUser("s15638@pjwstk.edu.pl", 546260188, "Oleksandr Kharchenko");
//        telegramClientUserService.addTelegramClientUser(user4);
// ************************************************************************

// *********************CONTENT USER CREATE********************************
//        TelegramClientUserService telegramClientUserService = new TelegramClientUserService();
//        TelegramAdminSuperUserService telegramAdminSuperUserService1 = new TelegramAdminSuperUserService();
//        TelegramAdminSuperUser initialSuperUser = telegramAdminSuperUserService1.getInitialSuperUser();
//        System.out.println(initialSuperUser.getIdUser());
//        TelegramClientUser telegramClientUser = telegramClientUserService.getTelegramClientUser(546240188);
//        System.out.println(telegramClientUser.getIdUser() + telegramClientUser.getIdUser());
//        telegramAdminSuperUserService1.addOperationalUser(telegramClientUser, initialSuperUser, "content");// ************************************************************************
// ************************************************************************

// *********************ADMIN USER CREATE**********************************
//        TelegramAdminSuperUser initialSuperUser = TelegramAdminSuperUserService.getInitialSuperUser();
//        System.out.println(initialSuperUser.getIdUser());
//        TelegramClientUser telegramClientUser = TelegramClientUserService.getTelegramClientUser(546210188);
//        System.out.println(telegramClientUser.getIdUser() + telegramClientUser.getIdUser());
//        TelegramAdminSuperUserService.addOperationalUser(telegramClientUser, initialSuperUser, "super");
// ************************************************************************

// *********************DISABLING OPERATIONAL USER**********************************
//        TelegramAdminSuperUser initialSuperUser = TelegramAdminSuperUserService.getInitialSuperUser();
//        TelegramAdminSuperUserService.disableOperationalUser(TelegramAdminSuperUserService.getTelegramOperUser(546210188), initialSuperUser);
// ************************************************************************

// *********************BAN TELEGRAM USER**********************************
//        TelegramClientUser telegramClientUser = TelegramClientUserService.getTelegramClientUser(546210188);
//        TelegramAdminSuperUserService.banTelegramClient(telegramClientUser, initialSuperUser);
// ************************************************************************

// *********************ADDING WEBITEM BY CONTENT ADMIN********************
        TelegramAdminSuperUserService telegramAdminSuperUserService = new TelegramAdminSuperUserService();
        TelegramAdminContentUserService telegramAdminContentUserService = new TelegramAdminContentUserService();
        TelegramAdminContentUser telegramOperationalUser = (TelegramAdminContentUser) telegramAdminSuperUserService.getTelegramOperUser(546240188);
//        Web2GameCode gameCode0 = new Web2GameCode("gameCode_MetroExodus", 50, 100, "OX8572123JEQES", "https://items.gog.com/metro_exodus/Metro_Exodus_PC_Enhanced_Logo_black.png", "Origin", "Metro Exodus");
//        Web2GameCode gameCode1 = new Web2GameCode("gameCode_TheWitcher3", 50, 150, "OX23JY87ETRKD7", "https://items.gog.com/the_witcher_3_wild_hunt/witcher_3_complete_edition_banner_en.png", "GOG", "The Witcher 3");
//        Web2GameCode gameCode2 = new Web2GameCode("gameCode_EldenRing", 50, 250, "OX23JK8UAT8TD1", "https://geekflare.com/wp-content/uploads/2022/07/Elden-Ring-Merch.png", "Steam", "Elden Ring");
//
//        telegramAdminContentUserService.addWebItem(gameCode0, telegramOperationalUser);
//        telegramAdminContentUserService.addWebItem(gameCode1, telegramOperationalUser);
//        telegramAdminContentUserService.addWebItem(gameCode2, telegramOperationalUser);
        Web3NFT nft = new Web3NFT("ukraine_nft", 5, 0.1, "0x11F0640bdb99E54Cbb7bE40E18460F9c9c16B957", "ETH", "ERC-721", 43, "epic", "https://i.seadn.io/gae/Vjbl-6yiwT3XzSwk7q4yffAfgPv1jwV8EMs5EGR8TYyFVDl9SXEHFAXz7sVQYV72J1DfXeo9MHIvYXSwCPP0v_lkHU8hHQ8254KS?auto=format&w=1000");
        telegramAdminContentUserService.addWebItem(nft, telegramOperationalUser);
//        Web2GiftCard giftCard0 = new Web2GiftCard("giftCard_BattleNet", 10, 489, "O5X36T43DF212G3JE8QES", "https://static.kinguin.net/cdn-cgi/image/w=1140,q=80,fit=scale-down,f=auto/media/category/b/n/bnetgc_1601920259_1603876637.jpg", "Battle Net", 500);
//        Web2GiftCard giftCard1 = new Web2GiftCard("giftCard_BattleNet", 80, 199, "O5X3XTT3HFX12L4JE8TEU", "https://static.kinguin.net/cdn-cgi/image/w=1140,q=80,fit=scale-down,f=auto/media/category/b/n/bnetgc_1601920259_1603876637.jpg", "Battle Net", 250);
//        Web2GiftCard giftCard2 = new Web2GiftCard("giftCard_Origin", 15, 99, "KKTS234932K43DF212G3JEIRSQ8QES", "https://images.g2a.com/uiadminimages/770x433/1x1x1/599554d5bbef/4a9424c9217a4e578407d5b4", "Origin", 100);
//        Web2GiftCard giftCard3 = new Web2GiftCard("giftCard_PStore", 50, 9, "YYSSH3D952K231FKLO3JEIRSQ8QHEJ", "https://images.g2a.com/1024x768/1x1x0/playstation-network-gift-card-200-pln-psn-poland-i10000070176495/59e737dc5bafe3a7782d06b4", "Playstation Store", 10);
//        telegramAdminContentUserService.addWebItem(giftCard0, telegramOperationalUser);
//        telegramAdminContentUserService.addWebItem(giftCard1, telegramOperationalUser);
//        telegramAdminContentUserService.addWebItem(giftCard2, telegramOperationalUser);
//        telegramAdminContentUserService.addWebItem(giftCard3, telegramOperationalUser);

// ************************************************************************

// *********************CREATING ORDER BY TELEGRAM CLIENT******************
//        TelegramClientUser telegramClientUser = TelegramClientUserService.getTelegramClientUser(546260188);
//        telegramClientUser.setCyptoWalletAdress("0xA8D7abFc7cd770000B0fD6F9a07D5d0Ac9361096");
//        TelegramClientUserService.updateTelegramClientUser(telegramClientUser);
//        WebItem webItem = WebItemService.getWebItem(1);
//        OrderService.createOrder(webItem, telegramClientUser);
// ************************************************************************

// *********************REALIZATION ORDER BY TELEGRAM CLIENT******************

//      ****************COMPLETED ORDER WITH PAYMENT***
//      ****************PAYMENT STAGE******************
//        Order order = OrderService.getOrders(3);
//        PaymentOrderService.createPayment(order, "crypto");
//      ***********************************************

//      ****************CANCELED ORDER*****************
//        Order order = OrderService.getOrders(3);
//        OrderService.cancelOrder(order);
//      ***********************************************


//      ****************PAYMENT COMPLETE CRYPTO********
//        try {
//            PaymentOrderService.completePaymentCrypto(order.getPayment(), "0x0d35734854daeac83c9c048055a440d36e2da526b3355888dce29397ad33d95f");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//      ***********************************************

//      ****************PAYMENT COMPLETE CARD**********
//            PaymentOrderService.completePaymentCard(order.getPayment());
//      ***********************************************

//      ****************ORDER STAGE******************
//        OrderService.sendOrder(order);
//      ***********************************************

//        TelegramAdminContentUser telegramOperationalUser = (TelegramAdminContentUser) TelegramAdminSuperUserService.getTelegramOperUser(546240188);
//        Web3NFT nft1 = new Web3NFT("kyivwar_nft", 10, 0.06, "0x495f947276749Ce646f68AC8c248420045cb7b5e", "ETH", "ERC-1155", 41, "epic", "https://i.seadn.io/gae/MtHJs6bVi2W66uN0Q2ylWkcU3qUd--FgMlS99iLD8ppDqBaTZ5VEeXiNgCpPPSOKaE64h--QJaVRKR2USa5xrmEvyL9mzVdD-gfr?auto=format&w=1000");
//        TelegramAdminContentUserService.addWebItem(nft1, telegramOperationalUser);
//        System.out.println(TelegramClientUserService.verifyIfExists(510188));

//        OrderCryptoReceiverSenderService orderCryptoSenderService = new OrderCryptoReceiverSenderService();
//        orderCryptoSenderService.getWalletInfo();
//        try {
//            orderCryptoSenderService.sendCryptoItem("0xA8D7abFc7cd770000B0fD6F9a07D5d0Ac9361096");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }


// ************************************************************************

//        try {
//            OrderCryptoReceiverSenderService c = new OrderCryptoReceiverSenderService();
//            c.verifyCryptoTxPayment(0.01,"0xc55763ed98222624769ca5c8a8d07fdd31204a6bc5724830a3fc712b362a8080");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


    }

}
