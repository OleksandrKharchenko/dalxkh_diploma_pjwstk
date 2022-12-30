package org.payments;

import org.orders.Order;
import org.orders.OrderCryptoReceiverSenderService;
import org.orders.OrderService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.users.TelegramClientUser;
import org.webitems.Web3NFT;
import org.webitems.WebItemService;

import java.io.IOException;
import java.util.List;

public class TelegramPaymentControllerHandler {
    public SendMessage createPaymentForOrderWithCrypto(CallbackQuery message) {
        Order order = OrderService.getOrders(Integer.parseInt(message.getData().substring(10)));
        CryptoPayment p = (CryptoPayment) PaymentOrderService.createPayment(order, "crypto");

        //*************KEYBOARD DEFINITION********************
        InlineKeyboardButton crypto = InlineKeyboardButton.builder()
                .text("Confirm").callbackData("confirmpay." + order.getIdOrder())
                .build();
        InlineKeyboardButton cancel = InlineKeyboardButton.builder()
                .text("Cancel").callbackData("cancel." + order.getIdOrder())
                .build();
        InlineKeyboardMarkup keyboardMarkup;
        keyboardMarkup = InlineKeyboardMarkup.builder().keyboardRow(List.of(crypto))
                .keyboardRow(List.of(cancel))
                .build();
        //****************************************************

        String createCryptoPaymentString = "Crypto payment for order number: <b>"+ order.getIdOrder()
                + "</b> is created. \nPlease, send exactly: <b>" + order.getCryptoEquivalentPrice() + " ETH</b>"
                + " to this: <b>" + p.getCryptoPaymentAddress() + "</b> address on Ethereum blockchain and click Confirm.";
        SendMessage sm = SendMessage.builder()
                .text(createCryptoPaymentString)
                .parseMode("HTML")
                .chatId(message.getMessage().getChatId())
                .replyMarkup(keyboardMarkup)
                .build();
        return sm;
    }

    public SendMessage provideTxHash(CallbackQuery message) {
        int idOrder = Integer.parseInt(message.getData().substring(11));
        //*************KEYBOARD DEFINITION********************
        InlineKeyboardButton confirm = InlineKeyboardButton.builder()
                .text("Verify").callbackData("verifytx." + idOrder)
                .build();
        InlineKeyboardMarkup keyboardMarkup;
        keyboardMarkup = InlineKeyboardMarkup.builder().keyboardRow(List.of(confirm))
                .build();
        //****************************************************
        String provideTxHash = "Got it.\nPlease, write your <b>txHash</b> below and click Verify.\nTxhash should looks like: 0x27477b5876e063650e2927916039aacf1c3c3b7c830415d0a084fc53ef6a9e6a";
        SendMessage sm = SendMessage.builder()
                .text(provideTxHash)
                .parseMode("HTML")
                .chatId(message.getMessage().getChatId())
                .replyMarkup(keyboardMarkup)
                .build();
        return sm;
    }

    public SendMessage verifyTxHash(CallbackQuery message) {
        int idOrder = Integer.parseInt(message.getData().substring(9));
        //*************KEYBOARD DEFINITION********************
        InlineKeyboardButton crypto = InlineKeyboardButton.builder()
                .text("Verify").callbackData("confirmverifytx." + idOrder)
                .build();
        InlineKeyboardMarkup keyboardMarkup;
        keyboardMarkup = InlineKeyboardMarkup.builder().keyboardRow(List.of(crypto))
                .build();
        //****************************************************
        OrderCryptoReceiverSenderService orderCryptoReceiverSenderService = new OrderCryptoReceiverSenderService();
        try {
            String verifyTxHashResult = orderCryptoReceiverSenderService.verifyCryptoTxPayment(OrderService.getOrders(idOrder).
                    getCryptoEquivalentPrice(), message.getMessage().getText());
            SendMessage sm = SendMessage.builder()
                    .text(verifyTxHashResult)
                    .parseMode("HTML")
                    .chatId(message.getMessage().getChatId())
                    .replyMarkup(keyboardMarkup)
                    .build();
            return sm;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //"⚙"️
}
