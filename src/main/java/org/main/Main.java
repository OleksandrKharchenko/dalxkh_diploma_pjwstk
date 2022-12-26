package org.main;


import org.main.*;
import org.users.*;
import org.orders.*;
import org.payments.*;
import org.webitems.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {


// *********************ENTITY GENERATE DB INITIATION**********************
//        HibernateSessionFactorySpawner.spawnInitSession();
// ************************************************************************

// *********************SUPER USER INITIATION******************************
//        String admEmail = "deepalexkh@gmail.com";
//        int admIdTelegramUser = 592510188;
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
//        TelegramClientUser telegramClientUser = TelegramClientUserService.getTelegramClientUser(546210188);
//        TelegramAdminContentUser telegramOperationalUser = (TelegramAdminContentUser) TelegramAdminSuperUserService.getTelegramOperUser(546240188);
//        Web2GameCode gameCode0 = new Web2GameCode("gameCode_MetroExodus", 50, 100, "OX8572123JEQES", null, "Metro Exodus");
//        TelegramAdminContentUserService.addWebItem(gameCode0, telegramOperationalUser);
// ************************************************************************

// *********************CREATING ORDER BY TELEGRAM CLIENT******************
//        TelegramClientUser telegramClientUser = TelegramClientUserService.getTelegramClientUser(546250188);
//        WebItem webItem = TelegramAdminContentUserService.getWebItems("Web2GameCode").get(0);
//        OrderService.createOrder(webItem, telegramClientUser);
// ************************************************************************

// *********************REALIZATION ORDER BY TELEGRAM CLIENT******************

//      ****************CANCELED ORDER*****************
//        Order order = OrderService.getOrders(1);
//        OrderService.cancelOrder(order);
//      ***********************************************

//      ****************COMPLETED ORDER WITH PAYMENT***
//      ****************PAYMENT STAGE******************
        Order order = OrderService.getOrders(1);
        PaymentService.createPayment(order, "crypto");



//      ****************ORDER STAGE******************
//        Order order = OrderService.getOrders(1);
//        OrderService.sendOrder(order);
//      ***********************************************

// ************************************************************************


//        Web3NFT nft0 = new Web3NFT("nft0_monkey", 5, "45x45424r32", "ETH", "ERC-1121", "45423423", "rare", null);
//        TelegramAdminContentUserService.addWebItem(nft0, telegramOperationalUser);
//        for (WebItem w : TelegramAdminContentUserService.getWebItems("Web2GameCode"))
//        {
//            Web2GameCode wg = (Web2GameCode) w;
//            System.out.println(wg.getName());
//        }

//        System.out.println(TelegramAdminContentUserService.getWebItems(1).getName());
//        WebItem gameCode0 = TelegramAdminContentUserService.getWebItems(1);
//        TelegramAdminContentUserService.deleteWebItem(gameCode0, telegramOperationalUser);

//        TelegramAdminSuperUserService.disableOperationalUser(TelegramAdminSuperUserService.getTelegramOperUser(546240188), initialSuperUser);
//
//        TelegramClientUser telegramClientUser = TelegramClientUserService.getTelegramClientUser(546210188);
//        TelegramAdminSuperUserService.banTelegramClient(telegramClientUser, initialSuperUser);



    }

}