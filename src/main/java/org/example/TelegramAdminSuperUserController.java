package org.example;

import java.util.Objects;

public abstract class TelegramAdminSuperUserController {

    public static String addOperationalUser(TelegramUser telegramUser, TelegramAdminSuperUser sessionSuperUser, String accessKey) {
        if (!Objects.equals(sessionSuperUser.getAccessSuperKey(), "9x09admin")){
            if (Objects.equals(accessKey, "content")) {

                return "Admin Content User added.";
            }
            if (Objects.equals(accessKey, "super")) {
                String accessSuperKey = new String("9x09admin");
                TelegramAdminSuperUser superUser = new TelegramAdminSuperUser(telegramUser.getIdUser(), telegramUser.getEmail(), telegramUser.getIdTelegramUser(), telegramUser.getDisplayName(), accessSuperKey);
                superUser.setAccessKey("super");
                superUser.setOperational(true);
                superUser.setAccessAddedBy(sessionSuperUser.getIdTelegramUser());
                return "Admin Super User added.";
            }
            else {
                return "Wrong access key provided.";
            }
        }
        return "You don't have permission to add Operational User. Wrong Access Super Key.";
    }

    public static String disableOperationalUser(TelegramOperationalUser operationalUser, TelegramAdminSuperUser sessionSuperUser) {
        if (operationalUser.isOperational()) {
            operationalUser.setOperational(false);
            operationalUser.setAccessKey("none");
            operationalUser.setAccessDisabledBy(sessionSuperUser.getIdTelegramUser());
            return "Operational user disabled.";
            }
        else {
            return "User is not operational.";
        }
    }

}
