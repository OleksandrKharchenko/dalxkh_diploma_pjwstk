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
import org.webitems.*;

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

        //COMMANDS
            if (update.getMessage().isCommand()) {
                if (update.getMessage().getText().equals("/start")) {
                    TelegramUserControllerHandler telegramUserControllerHandler = new TelegramUserControllerHandler();
                    SendMessage sm = telegramUserControllerHandler.addUserStartFlow(update.getMessage());
                    try {
                        execute(sm);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                } else if (update.getMessage().getText().equals("/set_up_my_email_address")) {
                    TelegramUserControllerHandler telegramUserControllerHandler = new TelegramUserControllerHandler();
                    SendMessage sm = telegramUserControllerHandler.prepareUpdateEmailAddress(update);
                    try {
                        execute(sm);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                } else if (update.getMessage().getText().equals("/set_up_my_crypto_address")) {
                    TelegramUserControllerHandler telegramUserControllerHandler = new TelegramUserControllerHandler();
                    SendMessage sm = telegramUserControllerHandler.prepareUpdateCryptoAddress(update);
                    try {
                        execute(sm);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        //CALLBACK QUERIES
        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("NFT")) {
                TelegramWebItemControllerHandler telegramWebItemControllerHandler = new TelegramWebItemControllerHandler();
                WebItemService webItemService = new WebItemService();
                List<WebItem> web3NFTs = webItemService.getWebItems("Web3NFT");
                SendPhoto sp;
                try {
                    for (WebItem w : web3NFTs) {
                        sp = telegramWebItemControllerHandler.getWeb3NFTs(update.getCallbackQuery().getMessage(), (Web3NFT) w);
                        execute(sp);
                    }
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else if (update.getCallbackQuery().getData().equals("GmC")) {
                TelegramWebItemControllerHandler telegramWebItemControllerHandler = new TelegramWebItemControllerHandler();
                WebItemService webItemService = new WebItemService();
                List<WebItem> web2GameCodes = webItemService.getWebItems("Web2GameCode");
                SendPhoto sp;
                try {
                    for (WebItem w : web2GameCodes) {
                        sp = telegramWebItemControllerHandler.getWeb2GameCodes(update.getCallbackQuery().getMessage(), (Web2GameCode) w);
                        execute(sp);
                    }
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }










            else if (update.getCallbackQuery().getData().substring(0, 6).equals("buyNFT")) {
                try {
                    SendMessage sm = new SendMessage();
                    TelegramClientUser telegramClientUser = TelegramClientUserService.getTelegramClientUser(Math.toIntExact(update.getCallbackQuery().getFrom().getId()));
                    TelegramOrderControllerHandler telegramOrderControllerHandler = new TelegramOrderControllerHandler();
                    if (telegramClientUser.isBanStatus()) {
                        sm.setText("You can't buy.\nReason: You are banned!");
                        sm.setChatId(update.getCallbackQuery().getMessage().getChatId());
                        execute(sm);
                    }
                    sm = telegramOrderControllerHandler.createOrderWithWeb3CryptoItem(update.getCallbackQuery(), telegramClientUser);
                    execute(sm);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else if (update.getCallbackQuery().getData().substring(0, 6).equals("cancel")) {
                TelegramOrderControllerHandler telegramOrderControllerHandler = new TelegramOrderControllerHandler();
                SendMessage sm = telegramOrderControllerHandler.cancelOrder(update.getCallbackQuery());
                try {
                    execute(sm);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else if (update.getCallbackQuery().getData().substring(0, 9).equals("cryptopay")) {
                TelegramPaymentControllerHandler telegramPaymentControllerHandler = new TelegramPaymentControllerHandler();
                SendMessage sm = telegramPaymentControllerHandler.createPaymentForOrderWithCrypto(update.getCallbackQuery());
                try {
                    execute(sm);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else if (update.getCallbackQuery().getData().substring(0, 10).equals("confirmpay")) {
                TelegramPaymentControllerHandler telegramPaymentControllerHandler = new TelegramPaymentControllerHandler();
                SendMessage sm = telegramPaymentControllerHandler.provideTxHash(update.getCallbackQuery());
                try {
                    execute(sm);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else if (update.getCallbackQuery().getData().substring(0, 8).equals("verifytx")) {
                TelegramPaymentControllerHandler telegramPaymentControllerHandler = new TelegramPaymentControllerHandler();
                SendMessage sm = telegramPaymentControllerHandler.verifyTxHash(update);
                try {
                    execute(sm);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else if (update.getCallbackQuery().getData().substring(0, 8).equals("claimnft")) {
                TelegramOrderControllerHandler telegramOrderControllerHandler = new TelegramOrderControllerHandler();
                SendMessage sm = telegramOrderControllerHandler.sendOrder(update.getCallbackQuery());
                try {
                    execute(sm);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        //REPLIES
        if (update.getMessage().isReply()) {
            if (update.getMessage().getReplyToMessage().getText().equals("/set_up_my_email_address")) {
                TelegramUserControllerHandler telegramUserControllerHandler = new TelegramUserControllerHandler();
                SendMessage sm = telegramUserControllerHandler.setEmailAddress(update);
                try {
                    execute(sm);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else if (update.getMessage().getReplyToMessage().getText().equals("/set_up_my_crypto_address")) {
                TelegramUserControllerHandler telegramUserControllerHandler = new TelegramUserControllerHandler();
                SendMessage sm = telegramUserControllerHandler.setCryptoAddress(update);
                try {
                    execute(sm);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else if (update.getMessage().getReplyToMessage().getText().contains("0x27477b5876e063650e2927916039aacf1c3c3b7c830415d0a084fc53ef6a9e6a")){
                TelegramPaymentControllerHandler telegramPaymentControllerHandler = new TelegramPaymentControllerHandler();
                SendMessage sm;
                sm = telegramPaymentControllerHandler.verifyTxHash(update);
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
