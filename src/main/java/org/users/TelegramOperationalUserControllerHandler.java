package org.users;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.main.HibernateSessionFactorySpawner;
import org.orders.Order;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TelegramOperationalUserControllerHandler {

    public boolean isOperational(long idTelegramUser){
        TelegramAdminSuperUserService telegramAdminSuperUserService = new TelegramAdminSuperUserService();
        try {
            return telegramAdminSuperUserService.isOperational(idTelegramUser);
        } catch (NoResultException noResultException){
            return false;
        }
    }

    public boolean isSuperUser(long idTelegramUser){
        TelegramAdminSuperUserService telegramAdminSuperUserService = new TelegramAdminSuperUserService();
        try {
            return telegramAdminSuperUserService.isSuperUser(idTelegramUser);
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
        InlineKeyboardButton unban;
        List<SendMessage> sendMessages = new ArrayList<SendMessage>();
        for (TelegramClientUser tg: telegramAdminSuperUserService.getTelegramClientUsers()) {
            //*************KEYBOARD DEFINITION********************
            unban = InlineKeyboardButton.builder()
                    .text("Unban").callbackData("unbanUsr." + tg.getIdTelegramUser())
                    .build();
            keyboardMarkup = InlineKeyboardMarkup.builder().keyboardRow(List.of(unban))
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

    public List<SendMessage> getTelegramClientPromoteUser(Update message){
        TelegramAdminSuperUserService telegramAdminSuperUserService = new TelegramAdminSuperUserService();
        InlineKeyboardMarkup keyboardMarkup;
        InlineKeyboardButton promote;
        List<SendMessage> sendMessages = new ArrayList<SendMessage>();
        for (TelegramClientUser tg: telegramAdminSuperUserService.getTelegramClientUsers()) {
            //*************KEYBOARD DEFINITION********************
            promote = InlineKeyboardButton.builder()
                    .text("Promote").callbackData("promote." + tg.getIdTelegramUser())
                    .build();
            keyboardMarkup = InlineKeyboardMarkup.builder().keyboardRow(List.of(promote))
                    .build();
            //****************************************************
            SendMessage sendMessage = SendMessage.builder()
                    .text("Name: <b>"
                            + tg.getDisplayName()
                            + "</b>\n"
                            + "IdTelegram: <b>"
                            + tg.getIdTelegramUser()
                            + "</b>\n"
                            )
                    .parseMode("HTML")
                    .chatId(message.getMessage().getChatId())
                    .replyMarkup(keyboardMarkup)
                    .build();
            sendMessages.add(sendMessage);
        }
        return sendMessages;
    }

    public List<SendMessage> getTelegramDissolveOperUser(Update message){
        TelegramAdminSuperUserService telegramAdminSuperUserService = new TelegramAdminSuperUserService();
        InlineKeyboardMarkup keyboardMarkup;
        InlineKeyboardButton dissolve;
        List<SendMessage> sendMessages = new ArrayList<SendMessage>();
        for (TelegramOperationalUser tg: telegramAdminSuperUserService.getTelegramOperUsers()) {
            //*************KEYBOARD DEFINITION********************
            dissolve = InlineKeyboardButton.builder()
                    .text("Dissolve").callbackData("dissolve." + tg.getIdTelegramUser())
                    .build();
            keyboardMarkup = InlineKeyboardMarkup.builder().keyboardRow(List.of(dissolve))
                    .build();
            //****************************************************
            SendMessage sendMessage = SendMessage.builder()
                    .text("Name: <b>"
                            + tg.getDisplayName()
                            + "</b>\n"
                            + "IdTelegram: <b>"
                            + tg.getIdTelegramUser()
                            + "</b>\n"
                    )
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

    public SendMessage promoteTelegramClientUser(Update message){
        TelegramAdminSuperUserService telegramAdminSuperUserService = new TelegramAdminSuperUserService();
        TelegramClientUserService telegramClientUserService = new TelegramClientUserService();
        TelegramAdminSuperUser telegramAdminSuperUser = (TelegramAdminSuperUser) telegramAdminSuperUserService.getTelegramOperUser(message.getCallbackQuery().getFrom().getId());
        String result = telegramAdminSuperUserService.addOperationalUser(telegramClientUserService.getTelegramClientUser(Long.parseLong(message.getCallbackQuery().getData().substring(8))),
                telegramAdminSuperUser, "content");
        SendMessage sm = SendMessage.builder()
                .text(result)
                .parseMode("HTML")
                .chatId(message.getCallbackQuery().getMessage().getChatId())
                .build();
        return sm;
    }

    public SendMessage dissolveTelegramOperUser(Update message){
        TelegramAdminSuperUserService telegramAdminSuperUserService = new TelegramAdminSuperUserService();
        TelegramAdminSuperUser telegramAdminSuperUser = (TelegramAdminSuperUser) telegramAdminSuperUserService.getTelegramOperUser(message.getCallbackQuery().getFrom().getId());
        String result = telegramAdminSuperUserService.dissolveOperationalUser(telegramAdminSuperUserService.getTelegramOperUser(Long.parseLong(message.getCallbackQuery().getData().substring(9))),
                telegramAdminSuperUser);
        SendMessage sm = SendMessage.builder()
                .text(result)
                .parseMode("HTML")
                .chatId(message.getCallbackQuery().getMessage().getChatId())
                .build();
        return sm;
    }

    public SendMessage whoami(Update message){
        String whoami;
        System.out.println("WHOAMI");
        if (isSuperUser(message.getMessage().getFrom().getId())){
            whoami = "You are Super User.";
        } else {
            whoami = "You are Content Admin User";
        }
        SendMessage sm = SendMessage.builder()
                .text(whoami)
                .parseMode("HTML")
                .chatId(message.getMessage().getChatId())
                .build();
        return sm;
    }

    public List<SendMessage> getOrders(Update message, String typeOf){
        TelegramAdminContentUserService telegramAdminContentUserService = new TelegramAdminContentUserService();
        InlineKeyboardMarkup keyboardMarkup;
        InlineKeyboardButton inlineKeyboardButtonOrder;
        List<SendMessage> sendMessages = new ArrayList<SendMessage>();
        List<Order> orderListByType = telegramAdminContentUserService.getOrders()
                .stream()
                .filter(order -> order.getState().equals(typeOf)).toList();
        for (Order order: orderListByType) {
            SendMessage sendMessage = SendMessage.builder()
                    .text("Order id <b>"
                            + order.getIdOrder()
                            + "</b>\n"
                            + "Client ID: <b>"
                            + order.getTelegramClientUser().getIdTelegramUser()
                            + "</b>\n"
                            + "Crypto Price: <b>"
                            + order.getCryptoEquivalentPrice()
                            + " ETH</b>\n"
                            + "Fiat Price: <b>"
                            + order.getUsdEquivalentPrice()
                            + " USD</b>\n"
                            + "Date Time created: <b>"
                            + order.getTimeStamp()
                            + "</b>\n"
                            + "State: <b>"
                            + order.getState()
                            + "</b>\n"
                    )
                    .parseMode("HTML")
                    .chatId(message.getMessage().getChatId())
                    .build();
            sendMessages.add(sendMessage);
        }
        return sendMessages;
    }

}
