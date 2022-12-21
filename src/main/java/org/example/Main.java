package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class Main {
    public static void main(String[] args) throws InterruptedException {
        //HibernateSessionFactorySpawner.spawnInitSession();
        String admEmail = "deepalexkh@gmail.com";
        int admIdTelegramUser = 592510188;
        String admDisplayName = "Alex Kharchenko";

//        TelegramAdminSuperUserController.initiateTelegramFirstSuperUser(admEmail, admIdTelegramUser, admDisplayName);

//        TelegramClientUser user1 = new TelegramClientUser("test@test.com", 546210188, "Elon Musk");
//        TelegramClientUserController.addTelegramClientUser(user1);
//        TelegramClientUser user2 = new TelegramClientUser("test2@test.com", 546240188, "Elon Tusk");
//        TelegramClientUserController.addTelegramClientUser(user2);
        System.out.println("*******TEST*******");
        TelegramAdminSuperUser initialSuperUser = TelegramAdminSuperUserController.getInitialSuperUser();
        System.out.println(initialSuperUser.getIdUser());
        TelegramClientUser telegramClientUser = TelegramClientUserController.getIClientUser(546240188);
        System.out.println(telegramClientUser.getIdUser());
        TelegramAdminSuperUserController.addOperationalUser(telegramClientUser, initialSuperUser, "content");





    }

}