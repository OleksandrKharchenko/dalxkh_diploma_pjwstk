package org.main;


import org.users.TelegramAdminSuperUser;
import org.users.TelegramAdminSuperUserController;
import org.users.TelegramClientUser;
import org.users.TelegramClientUserController;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        HibernateSessionFactorySpawner.spawnInitSession();
        String admEmail = "deepalexkh@gmail.com";
        int admIdTelegramUser = 592510188;
        String admDisplayName = "Alex Kharchenko";

        TelegramAdminSuperUserController.initiateTelegramFirstSuperUser(admEmail, admIdTelegramUser, admDisplayName);
//
//        TelegramClientUser user1 = new TelegramClientUser("test@test.com", 546210188, "Elon Musk");
//        TelegramClientUserController.addTelegramClientUser(user1);
//        TelegramClientUser user2 = new TelegramClientUser("test2@test.com", 546240188, "Elon Tusk");
//        TelegramClientUserController.addTelegramClientUser(user2);
//        System.out.println("*******TEST*******");
//        TelegramAdminSuperUser initialSuperUser = TelegramAdminSuperUserController.getInitialSuperUser();
//        System.out.println(initialSuperUser.getIdUser());
//        TelegramClientUser telegramClientUser = TelegramClientUserController.getTelegramClientUser(546240188);
//        System.out.println(telegramClientUser.getIdUser() + telegramClientUser.getIdUser());
//        TelegramAdminSuperUserController.addOperationalUser(telegramClientUser, initialSuperUser, "content");
//
//        TelegramAdminSuperUserController.disableOperationalUser(TelegramAdminSuperUserController.getTelegramOperUser(546240188), initialSuperUser);
//
//        TelegramClientUser telegramClientUser = TelegramClientUserController.getTelegramClientUser(546210188);
//        TelegramAdminSuperUserController.banTelegramClient(telegramClientUser, initialSuperUser);



    }

}