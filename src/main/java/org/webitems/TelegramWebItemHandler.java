package org.webitems;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class TelegramWebItemHandler {

    public SendPhoto getWeb3NFTs(Message message, Web3NFT web3NFT) {

        WebItemService webItemService = new WebItemService();


        //*************KEYBOARD DEFINITION********************
        InlineKeyboardButton buy = InlineKeyboardButton.builder()
                .text("Buy").callbackData("buy" + web3NFT.getContractAddress())
                .build();
        InlineKeyboardButton ethscan = InlineKeyboardButton.builder()
                .text("etherscan.io").callbackData("etherscan")
                .url("https://etherscan.io/address/" + web3NFT.getContractAddress())
                .build();
        InlineKeyboardMarkup keyboardMarkup;
        keyboardMarkup = InlineKeyboardMarkup.builder().keyboardRow(List.of(buy))
                .keyboardRow(List.of(ethscan)).build();
        //****************************************************

        SendPhoto sendPhoto = SendPhoto.builder().caption("Name: "
                        + web3NFT.getName()
                        + " "
                        + "\nPrice: <b>"
                        + web3NFT.getCryptoPrice()
                        + " "
                        + web3NFT.getBlockchain()
                        + " </b>")
                .photo(new InputFile(web3NFT.getImgPath()))
                .parseMode("HTML")
                .chatId(message.getChatId())
                .replyMarkup(keyboardMarkup)
                .build();
        return  sendPhoto;
    }


}
