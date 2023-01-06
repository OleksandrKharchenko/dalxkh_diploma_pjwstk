package org.telegramrobot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class MessageFlowGenerator {

    public SendMessage startFlow(String chatId){
        InlineKeyboardButton nft = InlineKeyboardButton.builder()
                .text("NFT's").callbackData("NFT")
                .build();
        InlineKeyboardButton gameCodes = InlineKeyboardButton.builder()
                .text("Game Codes").callbackData("GmC")
                .build();
        InlineKeyboardButton giftCards = InlineKeyboardButton.builder()
                .text("Gift Cards").callbackData("GfC")
                .build();
        InlineKeyboardMarkup keyboardM1;
        keyboardM1 = InlineKeyboardMarkup.builder().keyboardRow(List.of(nft)).keyboardRow(List.of(gameCodes))
                .keyboardRow(List.of(giftCards)).build();
        SendMessage sm = SendMessage.builder().chatId(chatId)
                .parseMode("HTML").text(EnvTelegramBotsVars.STARTDESCRIPTION)
                .replyMarkup(keyboardM1).build();
        return sm;
    }

    public SendMessage startFlowAdmin(long chatId){
        SendMessage sm = SendMessage.builder().chatId(chatId)
                .parseMode("HTML").text(EnvTelegramBotsVars.STARTDESCRIPTIONADMIN)
                .build();
        return sm;
    }

}
