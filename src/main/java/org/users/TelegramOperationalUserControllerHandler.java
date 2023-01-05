package org.users;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.main.HibernateSessionFactorySpawner;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class TelegramOperationalUserControllerHandler {

    public boolean isOperational(long idTelegramUser){
        TelegramAdminSuperUserService telegramAdminSuperUserService = new TelegramAdminSuperUserService();
        try {
            return telegramAdminSuperUserService.isOperational(idTelegramUser);
        } catch (NoResultException noResultException){
            return false;
        }
    }
    public List<SendMessage> getTelegramClientBanUsers(Update message){
        TelegramAdminSuperUserService telegramAdminSuperUserService = new TelegramAdminSuperUserService();
        InlineKeyboardMarkup keyboardMarkup;
        InlineKeyboardButton ban;
        List<SendMessage> sendMessages = new ArrayList<SendMessage>();
        for (TelegramClientUser tg: telegramAdminSuperUserService.getTelegramClientUsers()) {
            //*************KEYBOARD DEFINITION********************
            ban = InlineKeyboardButton.builder()
                    .text("Ban").callbackData("banUsr." + tg.getIdTelegramUser())
                    .build();
            keyboardMarkup = InlineKeyboardMarkup.builder().keyboardRow(List.of(ban))
                    .build();
            //****************************************************
            SendMessage sendMessage = SendMessage.builder()
                    .text("Name: <b>"
                            + tg.getDisplayName()
                            + "</b>\n"
                            + "IdTelegram: <b>"
                            + tg.getIdTelegramUser()
                            + "</b>\n"
                            + "Is banned: <b>"
                            + tg.isBanStatus()
                            + "</b>")
                    .parseMode("HTML")
                    .chatId(message.getMessage().getChatId())
                    .replyMarkup(keyboardMarkup)
                    .build();
            sendMessages.add(sendMessage);
        }
        return sendMessages;
    }

    public List<SendMessage> getTelegramClientUnBanUsers(Update message){
        TelegramAdminSuperUserService telegramAdminSuperUserService = new TelegramAdminSuperUserService();
        InlineKeyboardMarkup keyboardMarkup;
        InlineKeyboardButton ban;
        List<SendMessage> sendMessages = new ArrayList<SendMessage>();
        for (TelegramClientUser tg: telegramAdminSuperUserService.getTelegramClientUsers()) {
            //*************KEYBOARD DEFINITION********************
            ban = InlineKeyboardButton.builder()
                    .text("Unban").callbackData("unbanUsr." + tg.getIdTelegramUser())
                    .build();
            keyboardMarkup = InlineKeyboardMarkup.builder().keyboardRow(List.of(ban))
                    .build();
            //****************************************************
            SendMessage sendMessage = SendMessage.builder()
                    .text("Name: <b>"
                            + tg.getDisplayName()
                            + "</b>\n"
                            + "IdTelegram: <b>"
                            + tg.getIdTelegramUser()
                            + "</b>\n"
                            + "Is banned: <b>"
                            + tg.isBanStatus()
                            + "</b>")
                    .parseMode("HTML")
                    .chatId(message.getMessage().getChatId())
                    .replyMarkup(keyboardMarkup)
                    .build();
            sendMessages.add(sendMessage);
        }
        return sendMessages;
    }

    public SendMessage banTelegramClientUser(Update message){
        TelegramAdminSuperUserService telegramAdminSuperUserService = new TelegramAdminSuperUserService();
        TelegramClientUserService telegramClientUserService = new TelegramClientUserService();
        TelegramAdminSuperUser telegramAdminSuperUser = (TelegramAdminSuperUser) telegramAdminSuperUserService.getTelegramOperUser(message.getCallbackQuery().getFrom().getId());
        String result = telegramAdminSuperUserService.banTelegramClient(telegramClientUserService.getTelegramClientUser(Long.parseLong(message.getCallbackQuery().getData().substring(7))),
                telegramAdminSuperUser);
        SendMessage sm = SendMessage.builder()
                .text(result)
                .parseMode("HTML")
                .chatId(message.getCallbackQuery().getMessage().getChatId())
                .build();
        return sm;
    }

    public SendMessage unbanTelegramClientUser(Update message){
        TelegramAdminSuperUserService telegramAdminSuperUserService = new TelegramAdminSuperUserService();
        TelegramClientUserService telegramClientUserService = new TelegramClientUserService();
        TelegramAdminSuperUser telegramAdminSuperUser = (TelegramAdminSuperUser) telegramAdminSuperUserService.getTelegramOperUser(message.getCallbackQuery().getFrom().getId());
        String result = telegramAdminSuperUserService.unbanTelegramClient(telegramClientUserService.getTelegramClientUser(Long.parseLong(message.getCallbackQuery().getData().substring(9))),
                telegramAdminSuperUser);
        SendMessage sm = SendMessage.builder()
                .text(result)
                .parseMode("HTML")
                .chatId(message.getCallbackQuery().getMessage().getChatId())
                .build();
        return sm;
    }
}
