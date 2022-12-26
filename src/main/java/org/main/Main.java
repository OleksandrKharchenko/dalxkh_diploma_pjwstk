package org.main;


import org.hibernate.Session;
import org.orders.OrderController;
import org.users.*;
import org.webitems.Web2GameCode;
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
//        TelegramAdminSuperUserController.initiateTelegramFirstSuperUser(admEmail, admIdTelegramUser, admDisplayName);
// ************************************************************************

// *********************ADDING USERS***************************************
//        TelegramClientUser user1 = new TelegramClientUser("test@test.com", 546210188, "Elon Musk");
//        TelegramClientUserController.addTelegramClientUser(user1);
//        TelegramClientUser user2 = new TelegramClientUser("test2@test.com", 546240188, "Elon Tusk");
//        TelegramClientUserController.addTelegramClientUser(user2);
//        TelegramClientUser user3 = new TelegramClientUser("test3@test.com", 546250188, "Elon Client");
//        TelegramClientUserController.addTelegramClientUser(user3);
// ************************************************************************

// *********************CONTENT USER CREATE********************************
//        TelegramAdminSuperUser initialSuperUser = TelegramAdminSuperUserController.getInitialSuperUser();
//        System.out.println(initialSuperUser.getIdUser());
//        TelegramClientUser telegramClientUser = TelegramClientUserController.getTelegramClientUser(546240188);
//        System.out.println(telegramClientUser.getIdUser() + telegramClientUser.getIdUser());
//        TelegramAdminSuperUserController.addOperationalUser(telegramClientUser, initialSuperUser, "content");// ************************************************************************
// ************************************************************************

// *********************ADMIN USER CREATE**********************************
//        TelegramAdminSuperUser initialSuperUser = TelegramAdminSuperUserController.getInitialSuperUser();
//        System.out.println(initialSuperUser.getIdUser());
//        TelegramClientUser telegramClientUser = TelegramClientUserController.getTelegramClientUser(546210188);
//        System.out.println(telegramClientUser.getIdUser() + telegramClientUser.getIdUser());
//        TelegramAdminSuperUserController.addOperationalUser(telegramClientUser, initialSuperUser, "super");
// ************************************************************************

// *********************DISABLING OPERATIONAL USER**********************************
//        TelegramAdminSuperUser initialSuperUser = TelegramAdminSuperUserController.getInitialSuperUser();
//        TelegramAdminSuperUserController.disableOperationalUser(TelegramAdminSuperUserController.getTelegramOperUser(546210188), initialSuperUser);
// ************************************************************************

// *********************BAN TELEGRAM USER**********************************
//        TelegramClientUser telegramClientUser = TelegramClientUserController.getTelegramClientUser(546210188);
//        TelegramAdminSuperUserController.banTelegramClient(telegramClientUser, initialSuperUser);
// ************************************************************************

// *********************ADDING WEBITEM BY CONTENT ADMIN********************
//        TelegramClientUser telegramClientUser = TelegramClientUserController.getTelegramClientUser(546210188);
//        TelegramAdminContentUser telegramOperationalUser = (TelegramAdminContentUser) TelegramAdminSuperUserController.getTelegramOperUser(546240188);
//        Web2GameCode gameCode0 = new Web2GameCode("gameCode_MetroExodus", 50, 100, "OX8572123JEQES", null, "Metro Exodus");
//        TelegramAdminContentUserController.addWebItem(gameCode0, telegramOperationalUser);
// ************************************************************************

// *********************CREATING ORDER BY TELEGRAM CLIENT******************
//        TelegramClientUser telegramClientUser = TelegramClientUserController.getTelegramClientUser(546250188);
//        WebItem webItem = TelegramAdminContentUserController.getWebItems("Web2GameCode").get(0);
//        OrderController.createOrder(webItem, telegramClientUser);
// ************************************************************************



//        Web3NFT nft0 = new Web3NFT("nft0_monkey", 5, "45x45424r32", "ETH", "ERC-1121", "45423423", "rare", null);
//        TelegramAdminContentUserController.addWebItem(nft0, telegramOperationalUser);
//        for (WebItem w : TelegramAdminContentUserController.getWebItems("Web2GameCode"))
//        {
//            Web2GameCode wg = (Web2GameCode) w;
//            System.out.println(wg.getName());
//        }

//        System.out.println(TelegramAdminContentUserController.getWebItems(1).getName());
//        WebItem gameCode0 = TelegramAdminContentUserController.getWebItems(1);
//        TelegramAdminContentUserController.deleteWebItem(gameCode0, telegramOperationalUser);

//        TelegramAdminSuperUserController.disableOperationalUser(TelegramAdminSuperUserController.getTelegramOperUser(546240188), initialSuperUser);
//
//        TelegramClientUser telegramClientUser = TelegramClientUserController.getTelegramClientUser(546210188);
//        TelegramAdminSuperUserController.banTelegramClient(telegramClientUser, initialSuperUser);



    }

}