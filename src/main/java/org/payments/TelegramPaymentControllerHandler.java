package org.payments;

import org.orders.Order;
import org.orders.OrderCryptoReceiverSenderService;
import org.orders.OrderService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
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
        String provideTxHash = "Checking...⚙️ \nPlease, reply this message with your <b>TxHash</b>.\nTxHash should looks like: <b>0x27477b5876e063650e2927916039aacf1c3c3b7c830415d0a084fc53ef6a9e6a</b> " +
                "\nOrder number: <b>" + idOrder +"</b>";
        SendMessage sm = SendMessage.builder()
                .text(provideTxHash)
                .parseMode("HTML")
                .chatId(message.getMessage().getChatId())
                .build();
        return sm;
    }

    public SendMessage verifyTxHash(Update message) {
        System.out.println(message.getMessage().getReplyToMessage().getText().length());
        int idOrder = Integer.parseInt(message.getMessage().getReplyToMessage().getText().substring(167).trim());
        System.out.println(idOrder);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Processing...⚙️");
        CryptoPayment cryptoPayment = (CryptoPayment) OrderService.getOrders(idOrder).getPayment();
        SendMessage sm;
        //*************KEYBOARD DEFINITION1********************
        InlineKeyboardButton claim = InlineKeyboardButton.builder()
                .text("Claim NFT").callbackData("claimnft." + idOrder)
                .build();
        InlineKeyboardMarkup keyboardMarkup;
        keyboardMarkup = InlineKeyboardMarkup.builder().keyboardRow(List.of(claim))
                .build();
        //****************************************************
        //*************KEYBOARD DEFINITION2********************
        InlineKeyboardButton tryAgain = InlineKeyboardButton.builder()
                .text("Try again").callbackData("confirmpay." + idOrder)
                .build();
        InlineKeyboardMarkup keyboardMarkup1;
        keyboardMarkup1 = InlineKeyboardMarkup.builder().keyboardRow(List.of(tryAgain))
                .build();
        //****************************************************
        OrderCryptoReceiverSenderService orderCryptoReceiverSenderService = new OrderCryptoReceiverSenderService();
        String verifyTxHashResult = "something went wrong, try again.";
        try {
            verifyTxHashResult = orderCryptoReceiverSenderService.verifyCryptoTxPayment(OrderService.getOrders(idOrder).
                     getCryptoEquivalentPrice(), message.getMessage().getText());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stringBuilder.append("\n"+verifyTxHashResult);
        if(verifyTxHashResult.equals("OK")){
            String ok = "✅";
            stringBuilder.append(ok);
            sm = SendMessage.builder()
                    .text(stringBuilder.toString())
                    .parseMode("HTML")
                    .chatId(message.getMessage().getChatId())
                    .replyMarkup(keyboardMarkup)
                    .build();
            PaymentOrderService.completePaymentCard(cryptoPayment);
            return sm;
        }
        sm = SendMessage.builder()
                .text(stringBuilder.toString())
                .parseMode("HTML")
                .chatId(message.getMessage().getChatId())
                .replyMarkup(keyboardMarkup1)
                .build();
        return sm;
    }

}
