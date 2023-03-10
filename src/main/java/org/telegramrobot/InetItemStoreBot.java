package org.telegramrobot;

import okhttp3.OkHttpClient;
import org.orders.Order;
import org.orders.OrderCryptoReceiverSenderService;
import org.orders.OrderService;
import org.orders.TelegramOrderControllerHandler;
import org.payments.TelegramPaymentControllerHandler;
import org.telegram.telegrambots.bots.*;
import org.telegram.telegrambots.meta.api.methods.AnswerPreCheckoutQuery;
import org.telegram.telegrambots.meta.api.methods.invoices.SendInvoice;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.payments.SuccessfulPayment;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.users.*;
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
        //OPS USER CHECKER
        TelegramOperationalUserControllerHandler telegramOperationalUserControllerHandler = new TelegramOperationalUserControllerHandler();
        if(update.hasMessage() && telegramOperationalUserControllerHandler.isOperational(update.getMessage().getFrom().getId())
            || update.hasCallbackQuery() && telegramOperationalUserControllerHandler.isOperational(update.getCallbackQuery().getFrom().getId())) {
            SendMessage adminMessage;
            if (update.hasMessage()) {
                adminMessage = SendMessage.builder()
                        .text("YOU ARE AN OPS USER, PLEASE, USE https://t.me/inetitemstore_adminbot TO GET AND MODIFY DATA IN <b>INETITEMSTORE</b> DB.")
                        .parseMode("HTML")
                        .chatId(update.getMessage().getChatId())
                        .build();
                try {
                    execute(adminMessage);
                    throw new RuntimeException();
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
            adminMessage = SendMessage.builder()
                    .text("YOU ARE AN OPS USER, PLEASE, USE https://t.me/inetitemstore_adminbot TO GET AND MODIFY DATA IN <b>INETITEMSTORE</b> DB.")
                    .parseMode("HTML")
                    .chatId(update.getCallbackQuery().getMessage().getChatId())
                    .build();
            try {
                execute(adminMessage);
                throw new RuntimeException();
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        //PRECHECKOUTS
        if (update.hasPreCheckoutQuery()){
            System.out.println("CHECKOUT DETECTED");
            TelegramOrderControllerHandler telegramOrderControllerHandler = new TelegramOrderControllerHandler();
            AnswerPreCheckoutQuery answerPreCheckoutQuery;
            if (telegramOrderControllerHandler.orderIsCanceledOrCompleted(update)){
                answerPreCheckoutQuery = AnswerPreCheckoutQuery.builder()
                        .preCheckoutQueryId(update.getPreCheckoutQuery().getId())
                        .ok(false)
                        .errorMessage("Sorry, order is canceled or completed.")
                        .build();
            } else {
                answerPreCheckoutQuery = AnswerPreCheckoutQuery.builder()
                        .preCheckoutQueryId(update.getPreCheckoutQuery().getId())
                        .ok(true)
                        .build();
            }
            try {
                execute(answerPreCheckoutQuery);
            } catch (TelegramApiException e) {
                System.out.println(e);
                throw new RuntimeException(e);
            }
        }
        //PAYMENTS
        if (update.hasMessage() && update.getMessage().getSuccessfulPayment() != null){
            System.out.println("PAYMENTS DETECTED");
            TelegramOrderControllerHandler telegramOrderControllerHandler = new TelegramOrderControllerHandler();
            TelegramPaymentControllerHandler telegramPaymentControllerHandler = new TelegramPaymentControllerHandler();
            telegramPaymentControllerHandler.completeFiatPayment(update);
            SendMessage sm = telegramOrderControllerHandler.sendOrder(update);
            try {
                execute(sm);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }

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
                List<WebItem> web3NFTs = webItemService.getWebItemsByType("Web3NFT");
                SendPhoto sp;
                try {
                    for (WebItem w : web3NFTs) {
                        sp = telegramWebItemControllerHandler.getWeb3NFTs(update.getCallbackQuery().getMessage(), (Web3NFT) w);
                        execute(sp);
                    }
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else if (update.getCallbackQuery().getData().equals("GfC")) {
                TelegramWebItemControllerHandler telegramWebItemControllerHandler = new TelegramWebItemControllerHandler();
                WebItemService webItemService = new WebItemService();
                List<WebItem> web2GiftCard = webItemService.getWebItemsByType("Web2GiftCard");
                SendPhoto sp;
                try {
                    for (WebItem w : web2GiftCard) {
                        sp = telegramWebItemControllerHandler.getWeb2GiftCards(update.getCallbackQuery().getMessage(), (Web2GiftCard) w);
                        execute(sp);
                    }
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else if (update.getCallbackQuery().getData().equals("GmC")) {
                TelegramWebItemControllerHandler telegramWebItemControllerHandler = new TelegramWebItemControllerHandler();
                WebItemService webItemService = new WebItemService();
                List<WebItem> web2GameCodes = webItemService.getWebItemsByType("Web2GameCode");
                SendPhoto sp;
                try {
                    for (WebItem w : web2GameCodes) {
                        sp = telegramWebItemControllerHandler.getWeb2GameCodes(update.getCallbackQuery().getMessage(), (Web2GameCode) w);
                        execute(sp);
                    }
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else if (update.getCallbackQuery().getData().substring(0, 5).equals("buyGC")) {
                try {
                    SendMessage sm = new SendMessage();
                    TelegramClientUserService telegramClientUserService = new TelegramClientUserService();
                    TelegramClientUser telegramClientUser = telegramClientUserService.getTelegramClientUser(update.getCallbackQuery().getFrom().getId());
                    TelegramOrderControllerHandler telegramOrderControllerHandler = new TelegramOrderControllerHandler();
                    if (telegramClientUser.isBanStatus()) {
                        sm.setText("You can't buy.\nReason: You are banned!");
                        sm.setChatId(update.getCallbackQuery().getMessage().getChatId());
                        execute(sm);
                    }
                    sm = telegramOrderControllerHandler.createOrderWithWeb2Item(update.getCallbackQuery(), telegramClientUser);
                    execute(sm);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else if (update.getCallbackQuery().getData().substring(0, 6).equals("buyNFT")) {
                try {
                    SendMessage sm = new SendMessage();
                    TelegramClientUserService telegramClientUserService = new TelegramClientUserService();
                    TelegramClientUser telegramClientUser = telegramClientUserService.getTelegramClientUser(update.getCallbackQuery().getFrom().getId());
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
            } else if (update.getCallbackQuery().getData().substring(0, 7).equals("fiatpay")) {
                TelegramPaymentControllerHandler telegramPaymentControllerHandler = new TelegramPaymentControllerHandler();
                SendInvoice si = telegramPaymentControllerHandler.createPaymentForOrderWithFiat(update.getCallbackQuery());
                try {
                    execute(si);
                } catch (TelegramApiException e) {
                    System.out.println(e);
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
                SendMessage sm = telegramOrderControllerHandler.sendOrder(update);
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
