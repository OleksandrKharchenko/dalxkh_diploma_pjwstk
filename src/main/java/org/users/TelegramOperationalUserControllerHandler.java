package org.users;

import jakarta.persistence.NoResultException;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramOperationalUserControllerHandler {

    public boolean isOperational(long idTelegramUser){
        TelegramAdminSuperUserService telegramAdminSuperUserService = new TelegramAdminSuperUserService();
        try {
            return telegramAdminSuperUserService.isOperational(idTelegramUser);
        } catch (NoResultException noResultException){
            return false;
        }
    }
}
