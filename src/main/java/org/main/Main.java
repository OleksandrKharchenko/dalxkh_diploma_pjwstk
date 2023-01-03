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
//        String admEmail = "deepalexkh@gmail.com";
//        int admIdTelegramUser = 512510188;
//        String admDisplayName = "Alex Kharchenko";
//        TelegramAdminSuperUserService.initiateTelegramFirstSuperUser(admEmail, admIdTelegramUser, admDisplayName);
// ************************************************************************

// *********************ADDING USERS***************************************
//        TelegramClientUser user1 = new TelegramClientUser("test@test.com", 546210188, "Elon Musk");
//        TelegramClientUserService.addTelegramClientUser(user1);
//        TelegramClientUser user2 = new TelegramClientUser("test2@test.com", 546240188, "Elon Tusk");
//        TelegramClientUserService.addTelegramClientUser(user2);
//        TelegramClientUser user3 = new TelegramClientUser("test3@test.com", 546250188, "Elon Client");
//        TelegramClientUserService.addTelegramClientUser(user3);
//        TelegramClientUser user4 = new TelegramClientUser("s15638@pjwstk.edu.pl", 546260188, "Oleksandr Kharchenko");
//        TelegramClientUserService.addTelegramClientUser(user4);
// ************************************************************************

// *********************CONTENT USER CREATE********************************
//        TelegramAdminSuperUser initialSuperUser = TelegramAdminSuperUserService.getInitialSuperUser();
//        System.out.println(initialSuperUser.getIdUser());
//        TelegramClientUser telegramClientUser = TelegramClientUserService.getTelegramClientUser(546240188);
//        System.out.println(telegramClientUser.getIdUser() + telegramClientUser.getIdUser());
//        TelegramAdminSuperUserService.addOperationalUser(telegramClientUser, initialSuperUser, "content");// ************************************************************************
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
        TelegramAdminContentUser telegramOperationalUser = (TelegramAdminContentUser) TelegramAdminSuperUserService.getTelegramOperUser(546240188);
        Web2GameCode gameCode0 = new Web2GameCode("gameCode_MetroExodus", 50, 100, "OX8572123JEQES", "https://items.gog.com/metro_exodus/Metro_Exodus_PC_Enhanced_Logo_black.png", "Metro Exodus");
        Web2GameCode gameCode1 = new Web2GameCode("gameCode_TheWitcher3", 50, 150, "OX23JY87ETRKD7", "https://items.gog.com/the_witcher_3_wild_hunt/witcher_3_complete_edition_banner_en.png", "Witcher 3");

        TelegramAdminContentUserService.addWebItem(gameCode0, telegramOperationalUser);
        TelegramAdminContentUserService.addWebItem(gameCode1, telegramOperationalUser);
//        Web3NFT nft1 = new Web3NFT("christmas_nft", 100, 0.01, "0xd2fF891F5556c623F36a3F22B0E4815a3e36dc23", "ETH", "ERC-721", 38, "rare", "https://i.seadn.io/gcs/files/9b3993367af853569e8174efead8e6a4.png?auto=format&w=1000");
//        TelegramAdminContentUserService.addWebItem(nft1, telegramOperationalUser);
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
