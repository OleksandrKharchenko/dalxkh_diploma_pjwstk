package org.telegramrobot;

import org.telegram.telegrambots.bots.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.users.TelegramUserHandler;
import org.webitems.TelegramWebItemHandler;
import org.webitems.Web3NFT;
import org.webitems.WebItem;
import org.webitems.WebItemService;

import java.util.List;

public class InetItemStoreBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return EnvTelegramBotsVars.BOTUSERNAME;
    }

    @Override
    public String getBotToken() {
        return EnvTelegramBotsVars.TELEGRAMTOKENAPI;
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {


            if (update.getMessage().isCommand()) {
                if (update.getMessage().getText().equals("/start")) {
                    TelegramUserHandler telegramUserHandler = new TelegramUserHandler();
                    SendMessage sm = telegramUserHandler.addUserStartFlow(update.getMessage());
                    try {
                        execute(sm);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("NFT")) {
                TelegramWebItemHandler telegramWebItemHandler = new TelegramWebItemHandler();
                WebItemService webItemService = new WebItemService();
                List<WebItem> web3NFTs = webItemService.getWebItems("Web3NFT");
                SendPhoto sp;
                try {
                for (WebItem w: web3NFTs) {
                    sp = telegramWebItemHandler.getWeb3NFTs(update.getCallbackQuery().getMessage(), (Web3NFT) w);
                    execute(sp);
                }
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }



    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }
}
