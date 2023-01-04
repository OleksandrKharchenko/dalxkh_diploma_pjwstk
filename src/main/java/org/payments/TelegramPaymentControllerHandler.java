package org.payments;

import org.orders.Order;
import org.orders.OrderCryptoReceiverSenderService;
import org.orders.OrderService;
import org.telegram.telegrambots.meta.api.methods.invoices.SendInvoice;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.payments.LabeledPrice;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegramrobot.EnvTelegramBotsVars;
import org.webitems.Web2GameCode;
import org.webitems.Web2GiftCard;
import org.webitems.Web2Item;

import java.io.IOException;
import java.util.List;

public class TelegramPaymentControllerHandler {
    public SendMessage createPaymentForOrderWithCrypto(CallbackQuery message) {
        PaymentOrderService paymentOrderService = new PaymentOrderService();
        OrderService orderService = new OrderService();
        Order order = orderService.getOrders(Integer.parseInt(message.getData().substring(10)));
        CryptoPayment p = (CryptoPayment) paymentOrderService.createPayment(order, "crypto");

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


    public SendInvoice createPaymentForOrderWithFiat(CallbackQuery message) {
        PaymentOrderService paymentOrderService = new PaymentOrderService();
        OrderService orderService = new OrderService();
        Order order = orderService.getOrders(Integer.parseInt(message.getData().substring(8)));
        FiatPayment cp = (FiatPayment) paymentOrderService.createPayment(order, "fiat");
        Web2Item web2Item = (Web2Item) order.getWebItem();
        LabeledPrice labeledPrice = LabeledPrice.builder()
                .label("Order: " + order.getIdOrder())
                .amount(order.getUsdEquivalentPrice()*100)
                .build();
        SendInvoice si;
        if(order.getWebItem() instanceof Web2GameCode){
             si = SendInvoice.builder()
                    .title("Order: " + order.getIdOrder())
                    .providerToken(EnvTelegramBotsVars.PROVIDERTOKEN)
                    .chatId(message.getMessage().getChatId())
                    .photoUrl(web2Item.getImgPath())
                    .photoHeight(300)
                    .photoWidth(800)
                    .description(((Web2GameCode) order.getWebItem()).getPlatform() + " : " + ((Web2GameCode) order.getWebItem()).getGameName())
                            .payload("order: " + order.getIdOrder())
                            .currency("USD")
                            .price(labeledPrice)
                            .startParameter("startParam")
                            .build();
            return si;
        }
         si = SendInvoice.builder()
                .title("Order: " + order.getIdOrder())
                .providerToken(EnvTelegramBotsVars.PROVIDERTOKEN)
                .chatId(message.getMessage().getChatId())
                .photoUrl(web2Item.getImgPath())
                .photoHeight(300)
                .photoWidth(800)
                .description(((Web2GiftCard) order.getWebItem()).getPlatform() + " : " + ((Web2GiftCard) order.getWebItem()).getValueUsd() + " USD")
                .payload("order: " + order.getIdOrder())
                .currency("USD")
                .price(labeledPrice)
                .startParameter("startParam")
                .build();
        return si;
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

    public void completeFiatPayment(Update message){
        OrderService orderService = new OrderService();
        PaymentOrderService paymentOrderService = new PaymentOrderService();
        Order order = orderService.getOrders(Integer.parseInt(message.getMessage().getSuccessfulPayment().getInvoicePayload().substring(7)));
        paymentOrderService.completePaymentFiat(order.getPayment());
    }

    public SendMessage verifyTxHash(Update message) {
        PaymentOrderService paymentOrderService = new PaymentOrderService();
        OrderService orderService = new OrderService();
        int idOrder = Integer.parseInt(message.getMessage().getReplyToMessage().getText().substring(167).trim());
        System.out.println(idOrder);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Processing...⚙️");
        CryptoPayment cryptoPayment = (CryptoPayment) orderService.getOrders(idOrder).getPayment();
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
            verifyTxHashResult = orderCryptoReceiverSenderService.verifyCryptoTxPayment(orderService.getOrders(idOrder).
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
            try {
                paymentOrderService.completePaymentCrypto(cryptoPayment, message.getMessage().getText());
            } catch (Exception e) {
                String uniqueTxHashWarn = "\nBut, txHash was already used for another order, please, try again with unique txHash";
                stringBuilder.append(uniqueTxHashWarn);
                sm = SendMessage.builder()
                        .text(stringBuilder.toString())
                        .parseMode("HTML")
                        .chatId(message.getMessage().getChatId())
                        .replyMarkup(keyboardMarkup1)
                        .build();
                return sm;
            }
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
