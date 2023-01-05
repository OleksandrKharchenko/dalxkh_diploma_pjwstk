package org.telegramrobot;

import org.orders.TelegramOrderControllerHandler;
import org.payments.TelegramPaymentControllerHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerPreCheckoutQuery;
import org.telegram.telegrambots.meta.api.methods.invoices.SendInvoice;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.users.TelegramClientUser;
import org.users.TelegramClientUserService;
import org.users.TelegramOperationalUserControllerHandler;
import org.users.TelegramUserControllerHandler;
import org.webitems.*;

import java.util.List;

public class InetItemStoreBotAdmin extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return EnvTelegramBotsVars.BOTUSERNAMEADMIN;
    }

    @Override
    public String getBotToken() {
        return EnvTelegramBotsVars.TELEGRAMTOKENAPIADMIN;
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    @Override
    public void onUpdateReceived(Update update) {
        //OPS USER CHECKER
        TelegramOperationalUserControllerHandler telegramOperationalUserControllerHandler = new TelegramOperationalUserControllerHandler();
        if(update.hasMessage() && telegramOperationalUserControllerHandler.isOperational(update.getMessage().getFrom().getId())
                || update.hasCallbackQuery() && telegramOperationalUserControllerHandler.isOperational(update.getCallbackQuery().getFrom().getId())) {
            SendMessage sendMessage;
            sendMessage = SendMessage.builder()
                    .text(EnvTelegramBotsVars.STARTDESCRIPTIONADMIN)
                    .chatId(update.getMessage().getChatId())
                    .build();
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }






















        } else {
            SendMessage sendMessage;
            sendMessage = SendMessage.builder()
                    .text("You are not allowed to use this bot.")
                    .chatId(update.getMessage().getChatId())
                    .build();
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }


    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }
}
