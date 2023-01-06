package org.orders;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.users.TelegramClientUser;
import org.users.TelegramClientUserService;
import org.webitems.Web2GameCode;
import org.webitems.Web2GiftCard;
import org.webitems.Web3NFT;
import org.webitems.WebItemService;

import java.util.List;

public class TelegramOrderControllerHandler {

    public SendMessage createOrderWithWeb3CryptoItem(CallbackQuery message, TelegramClientUser telegramClientUser) {
        OrderService orderService = new OrderService();
        SendMessage sm;
        String cryptoWarning = "Before buying NFT, you need to update your crypto address. Click \uD83D\uDC49 /set_up_my_crypto_address";
        if (telegramClientUser.getCyptoWalletAdress() == null){
            sm = SendMessage.builder()
                    .text(cryptoWarning)
                    .parseMode("HTML")
                    .chatId(message.getMessage().getChatId())
                    .build();
            return sm;
        }
        WebItemService webItemService = new WebItemService();
        String isNotAvailable = "This item is currently unavailable, please, buy another or try again later.";
        if (!webItemService.isAvailable(Integer.parseInt(message.getData().substring(7)))){
            sm = SendMessage.builder()
                    .text(isNotAvailable)
                    .parseMode("HTML")
                    .chatId(message.getMessage().getChatId())
                    .build();
            return sm;
        }
        Order order = orderService.createOrder(webItemService.getWebItem(Integer.parseInt(message.getData().substring(7))), telegramClientUser);
        String orderString = "Order Number: <b>"+ order.getIdOrder() + "</b> \nClient: <b>" + telegramClientUser.getDisplayName() + "</b> \nClient crypto address: <b>" + telegramClientUser.getCyptoWalletAdress() + "</b> \nTelegram ID: <b>" + telegramClientUser.getIdTelegramUser() +
                "</b> \nItem Name: <b>" + order.getWebItem().getName() + "</b> \nContract Address: <b>" + ((Web3NFT) order.getWebItem()).getContractAddress() + "</b> \nToken ID: <b>" + ((Web3NFT) order.getWebItem()).getNFTTokenId() +  "</b> \nPrice ETH: <b>" + order.getCryptoEquivalentPrice() + " ETH</b>";
        //*************KEYBOARD DEFINITION********************
        InlineKeyboardButton crypto = InlineKeyboardButton.builder()
                .text("Pay with ETH").callbackData("cryptopay." + order.getIdOrder())
                .build();
        InlineKeyboardButton cancel = InlineKeyboardButton.builder()
                .text("Cancel").callbackData("cancel." + order.getIdOrder())
                .build();
        InlineKeyboardMarkup keyboardMarkup;
        keyboardMarkup = InlineKeyboardMarkup.builder().keyboardRow(List.of(crypto)).keyboardRow(List.of(cancel))
                .build();
        //****************************************************
        sm = SendMessage.builder()
                .text(orderString)
                .parseMode("HTML")
                .chatId(message.getMessage().getChatId())
                .replyMarkup(keyboardMarkup)
                .build();
        return sm;
    }



    public SendMessage createOrderWithWeb2Item(CallbackQuery message, TelegramClientUser telegramClientUser) {
        OrderService orderService = new OrderService();
        SendMessage sm;
        String cryptoWarning = "Before buying Game Codes or Gift Cards, you need to update your email address. Click \uD83D\uDC49 /set_up_my_email_address";
        if (telegramClientUser.getEmail() == null){
            sm = SendMessage.builder()
                    .text(cryptoWarning)
                    .parseMode("HTML")
                    .chatId(message.getMessage().getChatId())
                    .build();
            return sm;
        }
        WebItemService webItemService = new WebItemService();
        String isNotAvailable = "This item is currently unavailable, please, buy another or try again later.";
        if (!webItemService.isAvailable(Integer.parseInt(message.getData().substring(6)))){
            sm = SendMessage.builder()
                    .text(isNotAvailable)
                    .parseMode("HTML")
                    .chatId(message.getMessage().getChatId())
                    .build();
            return sm;
        }
        Order order = orderService.createOrder(webItemService.getWebItem(Integer.parseInt(message.getData().substring(6))), telegramClientUser);
        String orderString;
        if (order.getWebItem() instanceof Web2GameCode){
             orderString = "Order Number: <b>"+ order.getIdOrder() + "</b> \nClient: <b>" + telegramClientUser.getDisplayName() + "</b> \nClient email address: <b>" + telegramClientUser.getEmail() + "</b> \nTelegram ID: <b>" + telegramClientUser.getIdTelegramUser() +
                    "</b> \nGame: <b>" + ((Web2GameCode) order.getWebItem()).getGameName() + "</b> \nPlatform: <b>" + ((Web2GameCode) order.getWebItem()).getPlatform() + "</b> \nPrice USD: <b>" + order.getUsdEquivalentPrice() + " USD</b>";
        } else {
            orderString = "Order Number: <b>"+ order.getIdOrder() + "</b> \nClient: <b>" + telegramClientUser.getDisplayName() + "</b> \nClient email address: <b>" + telegramClientUser.getEmail() + "</b> \nTelegram ID: <b>" + telegramClientUser.getIdTelegramUser() +
                    "</b> \nPlatform: <b>" + ((Web2GiftCard) order.getWebItem()).getPlatform() + "</b> \nValue: <b>" + ((Web2GiftCard) order.getWebItem()).getValueUsd() + " USD</b> \nPrice USD: <b>" + order.getUsdEquivalentPrice() + " USD</b>";
        }
        //*************KEYBOARD DEFINITION********************
        InlineKeyboardButton fiat = InlineKeyboardButton.builder()
                .text("Pay with Fiat").callbackData("fiatpay." + order.getIdOrder())
                .build();
        InlineKeyboardButton cancel = InlineKeyboardButton.builder()
                .text("Cancel").callbackData("cancel." + order.getIdOrder())
                .build();
        InlineKeyboardMarkup keyboardMarkup;
        keyboardMarkup = InlineKeyboardMarkup.builder().keyboardRow(List.of(fiat)).keyboardRow(List.of(cancel))
                .build();
        //****************************************************
        sm = SendMessage.builder()
                .text(orderString)
                .parseMode("HTML")
                .chatId(message.getMessage().getChatId())
                .replyMarkup(keyboardMarkup)
                .build();
        return sm;
    }

    public SendMessage cancelOrder(CallbackQuery message) {
        OrderService orderService = new OrderService();
        Order order = orderService.getOrders(Integer.parseInt(message.getData().substring(7)));
        orderService.cancelOrder(order);
        String cancelString = "Order Number: <b>"+ order.getIdOrder() + "</b> is canceled. Thank you.\nTo go to Main Menu write /start or choose from command list.";
        SendMessage sm = SendMessage.builder()
                .text(cancelString)
                .parseMode("HTML")
                .chatId(message.getMessage().getChatId())
                .build();
        return sm;
    }

    public SendMessage sendOrder(Update message) {
        OrderService orderService = new OrderService();
        Order order;
        SendMessage sm;
        String sendString;
        if (message.hasMessage() && message.getMessage().hasSuccessfulPayment()) {
            order = orderService.getOrders(Integer.parseInt(message.getMessage().getSuccessfulPayment().getInvoicePayload().substring(7)));
            sendString = orderService.sendOrder(order);
            sm = SendMessage.builder()
                    .text(sendString)
                    .parseMode("HTML")
                    .chatId(message.getMessage().getChatId())
                    .build();
            return sm;
        }
        order = orderService.getOrders(Integer.parseInt(message.getCallbackQuery().getData().substring(9)));
        sendString = orderService.sendOrder(order);
        sm = SendMessage.builder()
                .text(sendString)
                .parseMode("HTML")
                .chatId(message.getCallbackQuery().getMessage().getChatId())
                .build();
        return sm;
    }

    public boolean orderIsCanceledOrCompleted(Update message) {
        OrderService orderService = new OrderService();
        Order order = orderService.getOrders(Integer.parseInt(message.getPreCheckoutQuery().getInvoicePayload().substring(7)));
        return order.getState().equals("canceled") || order.getState().equals("completed");
    }
}
