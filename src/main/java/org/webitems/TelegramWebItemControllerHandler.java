package org.webitems;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class TelegramWebItemControllerHandler {

    public SendPhoto getWeb3NFTs(Message message, Web3NFT web3NFT) {
        //*************KEYBOARD DEFINITION********************
        InlineKeyboardButton buy = InlineKeyboardButton.builder()
                .text("Buy").callbackData("buyNFT." + web3NFT.getIdItem())
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

    public SendPhoto getWeb2GameCodes(Message message, Web2GameCode gameCode) {
        //*************KEYBOARD DEFINITION********************
        InlineKeyboardButton buy = InlineKeyboardButton.builder()
                .text("Buy").callbackData("buyGC." + gameCode.getIdItem())
                .build();
        InlineKeyboardMarkup keyboardMarkup;
        keyboardMarkup = InlineKeyboardMarkup.builder().keyboardRow(List.of(buy))
                .build();
        //****************************************************

        SendPhoto sendPhoto = SendPhoto.builder().caption("Name: <b>"
                        + gameCode.getGameName()
                        + "</b>\nPlatform: <b>"
                        + gameCode.getPlatform()
                        + " </b>"
                        + "\nPrice: <b>"
                        + gameCode.getUsdPrice()
                        + " USD </b>"
                        + "\nQuantity: <b>"
                        + gameCode.getQuantity()
                        + " </b>")
                .photo(new InputFile(gameCode.getImgPath()))
                .parseMode("HTML")
                .chatId(message.getChatId())
                .replyMarkup(keyboardMarkup)
                .build();
        return  sendPhoto;
    }

}
