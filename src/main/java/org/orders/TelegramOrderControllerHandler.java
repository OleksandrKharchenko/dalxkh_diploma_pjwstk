package org.orders;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.users.TelegramClientUser;
import org.users.TelegramClientUserService;
import org.webitems.Web3NFT;
import org.webitems.WebItemService;

import java.util.List;

public class TelegramOrderControllerHandler {

    public SendMessage createOrderWithWeb3CryptoItem(CallbackQuery message, TelegramClientUser telegramClientUser) {
        WebItemService webItemService = new WebItemService();
        String orderPlaced = OrderService.createOrder(webItemService.getWebItem(Integer.parseInt(message.getData().substring(7))), telegramClientUser);
        //*************KEYBOARD DEFINITION********************
        InlineKeyboardButton crypto = InlineKeyboardButton.builder()
                .text("Pay with ETH").callbackData("crypto")
                .build();
        InlineKeyboardMarkup keyboardMarkup;
        keyboardMarkup = InlineKeyboardMarkup.builder().keyboardRow(List.of(crypto))
                .build();
        //****************************************************

        SendMessage sm = SendMessage.builder()
                .text(orderPlaced)
                .parseMode("HTML")
                .chatId(message.getMessage().getChatId())
                .replyMarkup(keyboardMarkup)
                .build();

        return sm;
    }



    public SendMessage createOrderWithWeb2Item(Message message) {





        //*************KEYBOARD DEFINITION********************
        InlineKeyboardButton crypto = InlineKeyboardButton.builder()
                .text("Crypto payment").callbackData("crypto")
                .build();
        InlineKeyboardMarkup keyboardMarkup;
        keyboardMarkup = InlineKeyboardMarkup.builder().keyboardRow(List.of(crypto))
                .build();
        //****************************************************

        SendMessage sendMessage = SendMessage.builder()
                .text("sdasd")
                .parseMode("HTML")
                .chatId(message.getChatId())
                .replyMarkup(keyboardMarkup)
                .build();

        return  sendMessage;
    }
}
