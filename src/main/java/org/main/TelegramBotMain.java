package org.main;

import okhttp3.OkHttpClient;
import org.orders.OrderCryptoReceiverSenderService;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.telegramrobot.InetItemStoreBot;


public class TelegramBotMain {
    public static void main(String[] args) {
        try {
//            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//            botsApi.registerBot(new InetItemStoreBot());
            OrderCryptoReceiverSenderService orderCryptoReceiverSenderService = new OrderCryptoReceiverSenderService();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
