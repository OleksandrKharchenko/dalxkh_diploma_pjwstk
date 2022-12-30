package org.telegramrobot;

import okhttp3.OkHttpClient;
import org.orders.Order;
import org.orders.OrderCryptoReceiverSenderService;
import org.orders.OrderService;
import org.orders.TelegramOrderControllerHandler;
import org.payments.TelegramPaymentControllerHandler;
import org.telegram.telegrambots.bots.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.users.TelegramClientUser;
import org.users.TelegramClientUserService;
import org.users.TelegramUserControllerHandler;
import org.webitems.TelegramWebItemControllerHandler;
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
                    TelegramUserControllerHandler telegramUserControllerHandler = new TelegramUserControllerHandler();
                    SendMessage sm = telegramUserControllerHandler.addUserStartFlow(update.getMessage());
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
                TelegramWebItemControllerHandler telegramWebItemControllerHandler = new TelegramWebItemControllerHandler();
                WebItemService webItemService = new WebItemService();
                List<WebItem> web3NFTs = webItemService.getWebItems("Web3NFT");
                SendPhoto sp;
                try {
                for (WebItem w: web3NFTs) {
                    sp = telegramWebItemControllerHandler.getWeb3NFTs(update.getCallbackQuery().getMessage(), (Web3NFT) w);
                    execute(sp);
                }
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else if (update.getCallbackQuery().getData().substring(0,6).equals("buyNFT")) {
                try {
                SendMessage sm = new SendMessage();
                TelegramClientUser telegramClientUser = TelegramClientUserService.getTelegramClientUser(Math.toIntExact(update.getCallbackQuery().getFrom().getId()));
                TelegramOrderControllerHandler telegramOrderControllerHandler = new TelegramOrderControllerHandler();
                if(telegramClientUser.isBanStatus()){
                    sm.setText("You can't buy.\nReason: You are banned!");
                    sm.setChatId(update.getCallbackQuery().getMessage().getChatId());
                    execute(sm);
                }
                sm = telegramOrderControllerHandler.createOrderWithWeb3CryptoItem(update.getCallbackQuery(), telegramClientUser);
                execute(sm);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else if (update.getCallbackQuery().getData().substring(0,6).equals("cancel")) {
                TelegramOrderControllerHandler telegramOrderControllerHandler = new TelegramOrderControllerHandler();
                SendMessage sm = telegramOrderControllerHandler.cancelOrder(update.getCallbackQuery());
                try {
                    execute(sm);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else if (update.getCallbackQuery().getData().substring(0,9).equals("cryptopay")){
                TelegramPaymentControllerHandler telegramPaymentControllerHandler = new TelegramPaymentControllerHandler();
                SendMessage sm = telegramPaymentControllerHandler.createPaymentForOrderWithCrypto(update.getCallbackQuery());
                try {
                    execute(sm);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else if (update.getCallbackQuery().getData().substring(0,10).equals("confirmpay")){
                TelegramPaymentControllerHandler telegramPaymentControllerHandler = new TelegramPaymentControllerHandler();
                SendMessage sm = telegramPaymentControllerHandler.provideTxHash(update.getCallbackQuery());
                try {
                    execute(sm);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else if (update.getCallbackQuery().getData().substring(0,8).equals("verifytx")) {
                TelegramPaymentControllerHandler telegramPaymentControllerHandler = new TelegramPaymentControllerHandler();
                SendMessage sm = telegramPaymentControllerHandler.verifyTxHash(update);
                try {
                    execute(sm);
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
