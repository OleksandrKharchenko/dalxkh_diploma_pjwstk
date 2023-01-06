package org.users;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.main.HibernateSessionFactorySpawner;
import org.orders.Order;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.webitems.Web2GameCode;
import org.webitems.Web2GiftCard;
import org.webitems.Web3NFT;
import org.webitems.WebItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TelegramOperationalUserControllerHandler {

    public boolean isOperational(long idTelegramUser){
        TelegramAdminSuperUserService telegramAdminSuperUserService = new TelegramAdminSuperUserService();
        try {
            return telegramAdminSuperUserService.isOperational(idTelegramUser);
        } catch (NoResultException noResultException){
            return false;
        }
    }

    public boolean isSuperUser(long idTelegramUser){
        TelegramAdminSuperUserService telegramAdminSuperUserService = new TelegramAdminSuperUserService();
        try {
            return telegramAdminSuperUserService.isSuperUser(idTelegramUser);
        } catch (NoResultException noResultException){
            return false;
        }
    }
    public List<SendMessage> getTelegramClientBanUsers(Update message){
        TelegramAdminSuperUserService telegramAdminSuperUserService = new TelegramAdminSuperUserService();
        InlineKeyboardMarkup keyboardMarkup;
        InlineKeyboardButton ban;
        List<SendMessage> sendMessages = new ArrayList<SendMessage>();
        for (TelegramClientUser tg: telegramAdminSuperUserService.getTelegramClientUsers()) {
            //*************KEYBOARD DEFINITION********************
            ban = InlineKeyboardButton.builder()
                    .text("Ban").callbackData("banUsr." + tg.getIdTelegramUser())
                    .build();
            keyboardMarkup = InlineKeyboardMarkup.builder().keyboardRow(List.of(ban))
                    .build();
            //****************************************************
            SendMessage sendMessage = SendMessage.builder()
                    .text("Name: <b>"
                            + tg.getDisplayName()
                            + "</b>\n"
                            + "IdTelegram: <b>"
                            + tg.getIdTelegramUser()
                            + "</b>\n"
                            + "Is banned: <b>"
                            + tg.isBanStatus()
                            + "</b>")
                    .parseMode("HTML")
                    .chatId(message.getMessage().getChatId())
                    .replyMarkup(keyboardMarkup)
                    .build();
            sendMessages.add(sendMessage);
        }
        return sendMessages;
    }

    public List<SendMessage> getTelegramClientUnBanUsers(Update message){
        TelegramAdminSuperUserService telegramAdminSuperUserService = new TelegramAdminSuperUserService();
        InlineKeyboardMarkup keyboardMarkup;
        InlineKeyboardButton unban;
        List<SendMessage> sendMessages = new ArrayList<SendMessage>();
        for (TelegramClientUser tg: telegramAdminSuperUserService.getTelegramClientUsers()) {
            //*************KEYBOARD DEFINITION********************
            unban = InlineKeyboardButton.builder()
                    .text("Unban").callbackData("unbanUsr." + tg.getIdTelegramUser())
                    .build();
            keyboardMarkup = InlineKeyboardMarkup.builder().keyboardRow(List.of(unban))
                    .build();
            //****************************************************
            SendMessage sendMessage = SendMessage.builder()
                    .text("Name: <b>"
                            + tg.getDisplayName()
                            + "</b>\n"
                            + "IdTelegram: <b>"
                            + tg.getIdTelegramUser()
                            + "</b>\n"
                            + "Is banned: <b>"
                            + tg.isBanStatus()
                            + "</b>")
                    .parseMode("HTML")
                    .chatId(message.getMessage().getChatId())
                    .replyMarkup(keyboardMarkup)
                    .build();
            sendMessages.add(sendMessage);
        }
        return sendMessages;
    }

    public List<SendMessage> getTelegramClientPromoteUser(Update message){
        TelegramAdminSuperUserService telegramAdminSuperUserService = new TelegramAdminSuperUserService();
        InlineKeyboardMarkup keyboardMarkup;
        InlineKeyboardButton promote;
        List<SendMessage> sendMessages = new ArrayList<SendMessage>();
        for (TelegramClientUser tg: telegramAdminSuperUserService.getTelegramClientUsers()) {
            //*************KEYBOARD DEFINITION********************
            promote = InlineKeyboardButton.builder()
                    .text("Promote").callbackData("promote." + tg.getIdTelegramUser())
                    .build();
            keyboardMarkup = InlineKeyboardMarkup.builder().keyboardRow(List.of(promote))
                    .build();
            //****************************************************
            SendMessage sendMessage = SendMessage.builder()
                    .text("Name: <b>"
                            + tg.getDisplayName()
                            + "</b>\n"
                            + "IdTelegram: <b>"
                            + tg.getIdTelegramUser()
                            + "</b>\n"
                            )
                    .parseMode("HTML")
                    .chatId(message.getMessage().getChatId())
                    .replyMarkup(keyboardMarkup)
                    .build();
            sendMessages.add(sendMessage);
        }
        return sendMessages;
    }

    public List<SendMessage> getTelegramDissolveOperUser(Update message){
        TelegramAdminSuperUserService telegramAdminSuperUserService = new TelegramAdminSuperUserService();
        InlineKeyboardMarkup keyboardMarkup;
        InlineKeyboardButton dissolve;
        List<SendMessage> sendMessages = new ArrayList<SendMessage>();
        for (TelegramOperationalUser tg: telegramAdminSuperUserService.getTelegramOperUsers()) {
            //*************KEYBOARD DEFINITION********************
            dissolve = InlineKeyboardButton.builder()
                    .text("Dissolve").callbackData("dissolve." + tg.getIdTelegramUser())
                    .build();
            keyboardMarkup = InlineKeyboardMarkup.builder().keyboardRow(List.of(dissolve))
                    .build();
            //****************************************************
            SendMessage sendMessage = SendMessage.builder()
                    .text("Name: <b>"
                            + tg.getDisplayName()
                            + "</b>\n"
                            + "IdTelegram: <b>"
                            + tg.getIdTelegramUser()
                            + "</b>\n"
                    )
                    .parseMode("HTML")
                    .chatId(message.getMessage().getChatId())
                    .replyMarkup(keyboardMarkup)
                    .build();
            sendMessages.add(sendMessage);
        }
        return sendMessages;
    }

    public SendMessage banTelegramClientUser(Update message){
        TelegramAdminSuperUserService telegramAdminSuperUserService = new TelegramAdminSuperUserService();
        TelegramClientUserService telegramClientUserService = new TelegramClientUserService();
        TelegramAdminSuperUser telegramAdminSuperUser = (TelegramAdminSuperUser) telegramAdminSuperUserService.getTelegramOperUser(message.getCallbackQuery().getFrom().getId());
        String result = telegramAdminSuperUserService.banTelegramClient(telegramClientUserService.getTelegramClientUser(Long.parseLong(message.getCallbackQuery().getData().substring(7))),
                telegramAdminSuperUser);
        SendMessage sm = SendMessage.builder()
                .text(result)
                .parseMode("HTML")
                .chatId(message.getCallbackQuery().getMessage().getChatId())
                .build();
        return sm;
    }

    public SendMessage unbanTelegramClientUser(Update message){
        TelegramAdminSuperUserService telegramAdminSuperUserService = new TelegramAdminSuperUserService();
        TelegramClientUserService telegramClientUserService = new TelegramClientUserService();
        TelegramAdminSuperUser telegramAdminSuperUser = (TelegramAdminSuperUser) telegramAdminSuperUserService.getTelegramOperUser(message.getCallbackQuery().getFrom().getId());
        String result = telegramAdminSuperUserService.unbanTelegramClient(telegramClientUserService.getTelegramClientUser(Long.parseLong(message.getCallbackQuery().getData().substring(9))),
                telegramAdminSuperUser);
        SendMessage sm = SendMessage.builder()
                .text(result)
                .parseMode("HTML")
                .chatId(message.getCallbackQuery().getMessage().getChatId())
                .build();
        return sm;
    }

    public SendMessage promoteTelegramClientUser(Update message){
        TelegramAdminSuperUserService telegramAdminSuperUserService = new TelegramAdminSuperUserService();
        TelegramClientUserService telegramClientUserService = new TelegramClientUserService();
        TelegramAdminSuperUser telegramAdminSuperUser = (TelegramAdminSuperUser) telegramAdminSuperUserService.getTelegramOperUser(message.getCallbackQuery().getFrom().getId());
        String result = telegramAdminSuperUserService.addOperationalUser(telegramClientUserService.getTelegramClientUser(Long.parseLong(message.getCallbackQuery().getData().substring(8))),
                telegramAdminSuperUser, "content");
        SendMessage sm = SendMessage.builder()
                .text(result)
                .parseMode("HTML")
                .chatId(message.getCallbackQuery().getMessage().getChatId())
                .build();
        return sm;
    }

    public SendMessage dissolveTelegramOperUser(Update message){
        TelegramAdminSuperUserService telegramAdminSuperUserService = new TelegramAdminSuperUserService();
        TelegramAdminSuperUser telegramAdminSuperUser = (TelegramAdminSuperUser) telegramAdminSuperUserService.getTelegramOperUser(message.getCallbackQuery().getFrom().getId());
        String result = telegramAdminSuperUserService.dissolveOperationalUser(telegramAdminSuperUserService.getTelegramOperUser(Long.parseLong(message.getCallbackQuery().getData().substring(9))),
                telegramAdminSuperUser);
        SendMessage sm = SendMessage.builder()
                .text(result)
                .parseMode("HTML")
                .chatId(message.getCallbackQuery().getMessage().getChatId())
                .build();
        return sm;
    }

    public SendMessage whoami(Update message){
        String whoami;
        System.out.println("WHOAMI");
        if (isSuperUser(message.getMessage().getFrom().getId())){
            whoami = "You are Super User.";
        } else {
            whoami = "You are Content Admin User";
        }
        SendMessage sm = SendMessage.builder()
                .text(whoami)
                .parseMode("HTML")
                .chatId(message.getMessage().getChatId())
                .build();
        return sm;
    }

    public SendMessage getStateOfOrders(Update message){
            //*************KEYBOARD DEFINITION********************
        InlineKeyboardButton keyboardButton0 = InlineKeyboardButton.builder()
                .text("Completed").callbackData("completed")
                .build();
        InlineKeyboardButton keyboardButton1 = InlineKeyboardButton.builder()
                .text("Canceled").callbackData("canceled")
                .build();
        InlineKeyboardButton keyboardButton2 = InlineKeyboardButton.builder()
                .text("Payment").callbackData("payment")
                .build();
        InlineKeyboardButton keyboardButton3 = InlineKeyboardButton.builder()
                .text("Initiated").callbackData("initiated")
                .build();
        InlineKeyboardMarkup keyboardMarkup = InlineKeyboardMarkup.builder().keyboardRow(List.of(keyboardButton0, keyboardButton1,
                        keyboardButton2, keyboardButton3))
                    .build();
            //****************************************************
            SendMessage sendMessage = SendMessage.builder()
                    .text("Sort by state of orders: ")
                    .parseMode("HTML")
                    .chatId(message.getMessage().getChatId())
                    .replyMarkup(keyboardMarkup)
                    .build();
        return sendMessage;
    }

    public SendMessage addWeb3NFTPreMessage(Update message){
        SendMessage sendMessage = SendMessage.builder()
                .text("In order to add NFT, please, reply this message in followed format: \n" +
                        "\n(name) ; (quantity) ; (crypto price) ; (contract address) ; (token standard) ; (token id) ; (quality) ; (img url) \n" +
                        "\nukraine_nft ; 5 ; 0.1 ; 0x11F0640bdb99E54Cbb7bE40E18460F9c9c16B957 ; ERC-721 ; 43 ; epic ; https://url.to.img/")
                .parseMode("HTML")
                .chatId(message.getCallbackQuery().getMessage().getChatId())
                .build();
        return sendMessage;
    }

    public SendMessage addWeb2GameCodePreMessage(Update message){
        SendMessage sendMessage = SendMessage.builder()
                .text("In order to add Game Code, please, reply this message in followed format: \n" +
                        "\n(name) ; (quantity) ; (usd price) ; (redeem code) ; (img url) ; (platform) ; (game name) \n" +
                        "\ngameCode_EldenRing ; 50 ; 250 ; OX23JK8UAT8TD1 ; https://url.to.img/ ; Steam ; Elden Ring")
                .parseMode("HTML")
                .chatId(message.getCallbackQuery().getMessage().getChatId())
                .build();
        return sendMessage;
    }

    public SendMessage addWeb2GiftCardPreMessage(Update message){
        SendMessage sendMessage = SendMessage.builder()
                .text("In order to add Gift Card, please, reply this message in followed format: \n" +
                        "\n(name) ; (quantity) ; (usd price) ; (redeem code) ; (img url) ; (platform) ; (usd value) \n" +
                        "\ngiftCard_BattleNet ; 10 ; 489 ; O5X36T43DF212G3JE8QES ; https://url.to.img/ ; Battle Net ; 500")
                .parseMode("HTML")
                .chatId(message.getCallbackQuery().getMessage().getChatId())
                .build();
        return sendMessage;
    }

    public SendMessage addTypeOfWebItem(Update message){
        //*************KEYBOARD DEFINITION********************
        InlineKeyboardButton keyboardButton0 = InlineKeyboardButton.builder()
                .text("NFT").callbackData("Web3NFTitemadd")
                .build();
        InlineKeyboardButton keyboardButton1 = InlineKeyboardButton.builder()
                .text("Game Code").callbackData("Web2GameCodeitemadd")
                .build();
        InlineKeyboardButton keyboardButton2 = InlineKeyboardButton.builder()
                .text("Gift Card").callbackData("Web2GiftCarditemadd")
                .build();
        InlineKeyboardMarkup keyboardMarkup = InlineKeyboardMarkup.builder().keyboardRow(List.of(keyboardButton0, keyboardButton1,
                        keyboardButton2))
                .build();
        //****************************************************
        SendMessage sendMessage = SendMessage.builder()
                .text("Select type of web item you want to add: ")
                .parseMode("HTML")
                .chatId(message.getMessage().getChatId())
                .replyMarkup(keyboardMarkup)
                .build();
        return sendMessage;
    }

    public SendMessage getTypeOfWebItems(Update message){
        //*************KEYBOARD DEFINITION********************
        InlineKeyboardButton keyboardButton0 = InlineKeyboardButton.builder()
                .text("NFT's").callbackData("Web3NFTitems")
                .build();
        InlineKeyboardButton keyboardButton1 = InlineKeyboardButton.builder()
                .text("Game Codes").callbackData("Web2GameCodeitems")
                .build();
        InlineKeyboardButton keyboardButton2 = InlineKeyboardButton.builder()
                .text("Gift Cards").callbackData("Web2GiftCarditems")
                .build();
        InlineKeyboardMarkup keyboardMarkup = InlineKeyboardMarkup.builder().keyboardRow(List.of(keyboardButton0, keyboardButton1,
                        keyboardButton2))
                .build();
        //****************************************************
        SendMessage sendMessage = SendMessage.builder()
                .text("Select type of web items: ")
                .parseMode("HTML")
                .chatId(message.getMessage().getChatId())
                .replyMarkup(keyboardMarkup)
                .build();
        return sendMessage;
    }

    public List<SendMessage> getOrders(Update message, String typeOf){
        TelegramAdminContentUserService telegramAdminContentUserService = new TelegramAdminContentUserService();
        InlineKeyboardMarkup keyboardMarkup;
        InlineKeyboardButton inlineKeyboardButtonOrder;
        List<SendMessage> sendMessages = new ArrayList<SendMessage>();
        List<Order> orderListByType = telegramAdminContentUserService.getOrders()
                .stream()
                .filter(order -> order.getState().contains(typeOf)).toList();
        for (Order order: orderListByType) {
            SendMessage sendMessage = SendMessage.builder()
                    .text(  "------------------------------------------------"
                            + "\nOrder id <b>"
                            + order.getIdOrder()
                            + "</b>\n"
                            + "Client ID: <b>"
                            + order.getTelegramClientUser().getIdTelegramUser()
                            + "</b>\n"
                            + "Crypto Price: <b>"
                            + order.getCryptoEquivalentPrice()
                            + " ETH</b>\n"
                            + "Fiat Price: <b>"
                            + order.getUsdEquivalentPrice()
                            + " USD</b>\n"
                            + "Date Time created: <b>"
                            + order.getTimeStamp()
                            + "</b>\n"
                            + "State: <b>"
                            + order.getState()
                            + "</b>\n"
                            + "------------------------------------------------"
                    )
                    .parseMode("HTML")
                    .chatId(message.getCallbackQuery().getMessage().getChatId())
                    .build();
            sendMessages.add(sendMessage);
        }
        return sendMessages;
    }

    public List<SendMessage> getAllOrders(Update message){
        TelegramAdminContentUserService telegramAdminContentUserService = new TelegramAdminContentUserService();
        InlineKeyboardMarkup keyboardMarkup;
        InlineKeyboardButton inlineKeyboardButtonOrder;
        List<SendMessage> sendMessages = new ArrayList<SendMessage>();
        List<Order> orderList = telegramAdminContentUserService.getOrders();
        for (Order order: orderList) {
            //*************KEYBOARD DEFINITION********************
            inlineKeyboardButtonOrder = InlineKeyboardButton.builder()
                    .text("Delete").callbackData("deleteo." + order.getIdOrder())
                    .build();
            keyboardMarkup = InlineKeyboardMarkup.builder().keyboardRow(List.of(inlineKeyboardButtonOrder))
                    .build();
            //****************************************************
            SendMessage sendMessage = SendMessage.builder()
                    .text(  "------------------------------------------------"
                            + "\nOrder id <b>"
                            + order.getIdOrder()
                            + "</b>\n"
                            + "Client ID: <b>"
                            + order.getTelegramClientUser().getIdTelegramUser()
                            + "</b>\n"
                            + "Crypto Price: <b>"
                            + order.getCryptoEquivalentPrice()
                            + " ETH</b>\n"
                            + "Fiat Price: <b>"
                            + order.getUsdEquivalentPrice()
                            + " USD</b>\n"
                            + "Date Time created: <b>"
                            + order.getTimeStamp()
                            + "</b>\n"
                            + "State: <b>"
                            + order.getState()
                            + "</b>\n"
                            + "------------------------------------------------"
                    )
                    .parseMode("HTML")
                    .chatId(message.getMessage().getChatId())
                    .replyMarkup(keyboardMarkup)
                    .build();
            sendMessages.add(sendMessage);
        }
        return sendMessages;
    }

    public SendMessage deleteOrder(Update message){
        TelegramAdminContentUserService telegramAdminContentUserService = new TelegramAdminContentUserService();
        String result = telegramAdminContentUserService.deleteOrder(Integer.parseInt(message.getCallbackQuery().getData().substring(8)));
        SendMessage sm = SendMessage.builder()
                .text(result)
                .parseMode("HTML")
                .chatId(message.getCallbackQuery().getMessage().getChatId())
                .build();
        return sm;
    }

    public SendMessage deleteWebItem(Update message){
        TelegramAdminContentUserService telegramAdminContentUserService = new TelegramAdminContentUserService();
        String result = telegramAdminContentUserService.deleteWebItem(Integer.parseInt(message.getCallbackQuery().getData().substring(8)));
        SendMessage sm = SendMessage.builder()
                .text(result)
                .parseMode("HTML")
                .chatId(message.getCallbackQuery().getMessage().getChatId())
                .build();
        return sm;
    }

    public SendMessage addWebItemNFT(Update message){
        TelegramAdminContentUserService telegramAdminContentUserService = new TelegramAdminContentUserService();
        String str = message.getMessage().getText().trim();
        String[] splited = str.split(";");
        SendMessage sm;
        if (splited.length != 8) {
            sm = SendMessage.builder()
                    .text("Wrong format, please, try again.")
                    .parseMode("HTML")
                    .chatId(message.getMessage().getChatId())
                    .build();
            return sm;
        }
        try {
            System.out.println(Long.parseLong(splited[1].trim()));
            System.out.println(Double.parseDouble(splited[2].trim()));
            System.out.println(Long.parseLong(splited[5].trim()));
            try {
                if(!splited[7].trim().substring(0, 8).equals("https://")){
                    sm = SendMessage.builder()
                            .text("Wrong url address format, please, try again.")
                            .parseMode("HTML")
                            .chatId(message.getMessage().getChatId())
                            .build();
                    return sm;
                }
            } catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
                sm = SendMessage.builder()
                        .text("Wrong url address format, please, try again.")
                        .parseMode("HTML")
                        .chatId(message.getMessage().getChatId())
                        .build();
                return sm;
            }
        } catch (NumberFormatException numberFormatException){
            sm = SendMessage.builder()
                    .text("Wrong format (quantity) or (crypto price) or (token id) please, try again.")
                    .parseMode("HTML")
                    .chatId(message.getMessage().getChatId())
                    .build();
            return sm;
        }
        String name = splited[0].trim();
        long quantity = Long.parseLong(splited[1].trim());
        double cryptoPrice = Double.parseDouble(splited[2].trim());
        String contactAddress = splited[3].trim();
        String tokenStandard = splited[4].trim();
        long tokenId = Long.parseLong(splited[5].trim());
        String quality = splited[6].trim();
        String imgPath = splited[7].trim();
        Web3NFT web3NFT = new Web3NFT(name, quantity, cryptoPrice,
                contactAddress, "ETH", tokenStandard, tokenId, quality, imgPath);
        web3NFT.setAddedBy(message.getMessage().getFrom().getId());
        String result = telegramAdminContentUserService.addWebItem(web3NFT);
        sm = SendMessage.builder()
                .text(result)
                .parseMode("HTML")
                .chatId(message.getMessage().getChatId())
                .build();
        return sm;
    }

    public SendMessage addWebItemGameCode(Update message){
        TelegramAdminContentUserService telegramAdminContentUserService = new TelegramAdminContentUserService();
        String str = message.getMessage().getText().trim();
        String[] splited = str.split(";");
        SendMessage sm;
        if (splited.length != 7) {
            sm = SendMessage.builder()
                    .text("Wrong format, please, try again.")
                    .parseMode("HTML")
                    .chatId(message.getMessage().getChatId())
                    .build();
            return sm;
        }
        try {
            System.out.println(Long.parseLong(splited[1].trim()));
            System.out.println(Integer.parseInt(splited[2].trim()));
            try {
                if(!splited[4].trim().substring(0, 8).equals("https://")){
                    sm = SendMessage.builder()
                            .text("Wrong url address format, please, try again.")
                            .parseMode("HTML")
                            .chatId(message.getMessage().getChatId())
                            .build();
                    return sm;
                }
            } catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
                sm = SendMessage.builder()
                        .text("Wrong url address format, please, try again.")
                        .parseMode("HTML")
                        .chatId(message.getMessage().getChatId())
                        .build();
                return sm;
            }
        } catch (NumberFormatException numberFormatException){
            sm = SendMessage.builder()
                    .text("Wrong format (quantity) or (usd price) please, try again.")
                    .parseMode("HTML")
                    .chatId(message.getMessage().getChatId())
                    .build();
            return sm;
        }
        String name = splited[0].trim();
        long quantity = Long.parseLong(splited[1].trim());
        int usdPrice = Integer.parseInt(splited[2].trim());
        String redeemCode = splited[3].trim();
        String imgPath = splited[4].trim();
        String platform = splited[5].trim();
        String gameName = splited[6].trim();
        Web2GameCode web2GameCode = new Web2GameCode(name, quantity, usdPrice,
                redeemCode, imgPath, platform, gameName);
        web2GameCode.setAddedBy(message.getMessage().getFrom().getId());
        String result = telegramAdminContentUserService.addWebItem(web2GameCode);
        sm = SendMessage.builder()
                .text(result)
                .parseMode("HTML")
                .chatId(message.getMessage().getChatId())
                .build();
        return sm;
    }

    public SendMessage addWebItemGiftCard(Update message){
        TelegramAdminContentUserService telegramAdminContentUserService = new TelegramAdminContentUserService();
        String str = message.getMessage().getText().trim();
        String[] splited = str.split(";");
        SendMessage sm;
        if (splited.length != 7) {
            sm = SendMessage.builder()
                    .text("Wrong format, please, try again.")
                    .parseMode("HTML")
                    .chatId(message.getMessage().getChatId())
                    .build();
            return sm;
        }
        try {
            System.out.println(Long.parseLong(splited[1].trim()));
            System.out.println(Double.parseDouble(splited[2].trim()));
            System.out.println(Integer.parseInt(splited[6].trim()));
            try {
                if(!splited[4].trim().substring(0, 8).equals("https://")){
                    sm = SendMessage.builder()
                            .text("Wrong url address format, please, try again.")
                            .parseMode("HTML")
                            .chatId(message.getMessage().getChatId())
                            .build();
                    return sm;
                }
            } catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
                sm = SendMessage.builder()
                        .text("Wrong url address format, please, try again.")
                        .parseMode("HTML")
                        .chatId(message.getMessage().getChatId())
                        .build();
                return sm;
            }
        } catch (NumberFormatException numberFormatException){
            sm = SendMessage.builder()
                    .text("Wrong format (quantity) or (usd price) or (usd value) please, try again.")
                    .parseMode("HTML")
                    .chatId(message.getMessage().getChatId())
                    .build();
            return sm;
        }
        String name = splited[0].trim();
        long quantity = Long.parseLong(splited[1].trim());
        int usdPrice = Integer.parseInt(splited[2].trim());
        String redeemCode = splited[3].trim();
        String imgPath = splited[4].trim();
        String platform = splited[5].trim();
        int usdValue = Integer.parseInt(splited[6].trim());
        Web2GiftCard web2GiftCard = new Web2GiftCard(name, quantity, usdPrice,
                redeemCode, imgPath, platform, usdValue);
        web2GiftCard.setAddedBy(message.getMessage().getFrom().getId());
        String result = telegramAdminContentUserService.addWebItem(web2GiftCard);
        sm = SendMessage.builder()
                .text(result)
                .parseMode("HTML")
                .chatId(message.getMessage().getChatId())
                .build();
        return sm;
    }

    public List<SendPhoto> getWebItems(Update message){
        TelegramAdminContentUserService telegramAdminContentUserService = new TelegramAdminContentUserService();
        InlineKeyboardMarkup keyboardMarkup;
        InlineKeyboardButton inlineKeyboardButtonWebItem;
        List<SendPhoto> sendPhotos = new ArrayList<SendPhoto>();
        List<WebItem> webItems = telegramAdminContentUserService.getWebItems();
            for (WebItem webItem: webItems) {
                //*************KEYBOARD DEFINITION********************
                inlineKeyboardButtonWebItem = InlineKeyboardButton.builder()
                        .text("Delete").callbackData("deletew." + webItem.getIdItem())
                        .build();
                keyboardMarkup = InlineKeyboardMarkup.builder().keyboardRow(List.of(inlineKeyboardButtonWebItem))
                        .build();
                //****************************************************
                if (webItem instanceof Web3NFT) {
                    SendPhoto sendPhoto = SendPhoto.builder()
                            .caption(  "------------------------------------------------"
                                    + "\nName: <b>"
                                    + ((Web3NFT) webItem).getName()
                                    + "</b>\n"
                                    + "Id Item: <b>"
                                    + ((Web3NFT) webItem).getIdItem()
                                    + "</b>\n"
                                    +  "\nQuantity: <b>"
                                    + ((Web3NFT) webItem).getQuantity()
                                    + "</b>\n"
                                    + "Blockchain: <b>"
                                    + ((Web3NFT) webItem).getBlockchain()
                                    + "</b>\n"
                                    + "Contract Address: <b>"
                                    + ((Web3NFT) webItem).getContractAddress()
                                    + "</b>\n"
                                    + "Token Standard: <b>"
                                    + ((Web3NFT) webItem).getTokenStandard()
                                    + "</b>\n"
                                    + "NFTTokenId: <b>"
                                    + ((Web3NFT) webItem).getNFTTokenId()
                                    + "</b>\n"
                                    + "Quality: <b>"
                                    + ((Web3NFT) webItem).getQuality()
                                    + "</b>\n"
                                    + "Added by: <b>"
                                    + ((Web3NFT) webItem).getAddedBy()
                                    + "</b>\n"
                                    + "Crypto Price: <b>"
                                    + ((Web3NFT) webItem).getCryptoPrice()
                                    + " ETH</b>\n"
                                    + "Img Path: <b>"
                                    + ((Web3NFT) webItem).getImgPath()
                                    + "</b>\n"
                                    + "------------------------------------------------"
                            )
                            .photo(new InputFile(((Web3NFT) webItem).getImgPath()))
                            .parseMode("HTML")
                            .replyMarkup(keyboardMarkup)
                            .chatId(message.getMessage().getChatId())
                            .build();
                    sendPhotos.add(sendPhoto);
                }
                if (webItem instanceof Web2GameCode) {
                    SendPhoto sendPhoto = SendPhoto.builder()
                            .caption(  "------------------------------------------------"
                                    + "\nName: <b>"
                                    + ((Web2GameCode) webItem).getName()
                                    + "</b>\n"
                                    + "Id Item:: <b>"
                                    + ((Web2GameCode) webItem).getIdItem()
                                    + "</b>\n"
                                    +  "\nQuantity: <b>"
                                    + ((Web2GameCode) webItem).getQuantity()
                                    + "</b>\n"
                                    + "Game Name: <b>"
                                    + ((Web2GameCode) webItem).getGameName()
                                    + "</b>\n"
                                    + "Platform: <b>"
                                    + ((Web2GameCode) webItem).getPlatform()
                                    + "</b>\n"
                                    + "Added by: <b>"
                                    + ((Web2GameCode) webItem).getAddedBy()
                                    + "</b>\n"
                                    + "Fiat Price: <b>"
                                    + ((Web2GameCode) webItem).getUsdPrice()
                                    + " USD</b>\n"
                                    + "Img Path: <b>"
                                    + ((Web2GameCode) webItem).getImgPath()
                                    + "</b>\n"
                                    + "Redeem Code: <b>"
                                    + ((Web2GameCode) webItem).getRedeemCode()
                                    + "</b>\n"
                                    + "------------------------------------------------"
                            )
                            .photo(new InputFile(((Web2GameCode) webItem).getImgPath()))
                            .parseMode("HTML")
                            .replyMarkup(keyboardMarkup)
                            .chatId(message.getMessage().getChatId())
                            .build();
                    sendPhotos.add(sendPhoto);
                }
                if (webItem instanceof Web2GiftCard) {
                    SendPhoto sendPhoto = SendPhoto.builder()
                            .caption(  "------------------------------------------------"
                                    + "\nName: <b>"
                                    + ((Web2GiftCard) webItem).getName()
                                    + "</b>\n"
                                    + "Id Item: <b>"
                                    + ((Web2GiftCard) webItem).getIdItem()
                                    + "</b>\n"
                                    +  "\nQuantity: <b>"
                                    + ((Web2GiftCard) webItem).getQuantity()
                                    + "</b>\n"
                                    + "Platform: <b>"
                                    + ((Web2GiftCard) webItem).getPlatform()
                                    + "</b>\n"
                                    + "Value USD: <b>"
                                    + ((Web2GiftCard) webItem).getValueUsd()
                                    + "</b>\n"
                                    + "Added by: <b>"
                                    + ((Web2GiftCard) webItem).getAddedBy()
                                    + "</b>\n"
                                    + "Fiat Price: <b>"
                                    + ((Web2GiftCard) webItem).getUsdPrice()
                                    + " USD</b>\n"
                                    + "Img Path: <b>"
                                    + ((Web2GiftCard) webItem).getImgPath()
                                    + "</b>\n"
                                    + "Redeem Code: <b>"
                                    + ((Web2GiftCard) webItem).getRedeemCode()
                                    + "</b>\n"
                                    + "------------------------------------------------"
                            )
                            .photo(new InputFile(((Web2GiftCard) webItem).getImgPath()))
                            .parseMode("HTML")
                            .replyMarkup(keyboardMarkup)
                            .chatId(message.getMessage().getChatId())
                            .build();
                    sendPhotos.add(sendPhoto);
                }
            }
        return sendPhotos;
    }

    public List<SendPhoto> getWebItemsByType(Update message, String typeOf){
        TelegramAdminContentUserService telegramAdminContentUserService = new TelegramAdminContentUserService();
        List<SendPhoto> sendPhotos = new ArrayList<SendPhoto>();
        List<WebItem> webItems = telegramAdminContentUserService.getWebItemsByType(typeOf);
        if (webItems.stream().anyMatch(webItem -> webItem instanceof Web2GameCode)){
            for (WebItem web2GameCode: webItems) {
                SendPhoto sendPhoto = SendPhoto.builder()
                        .caption(  "------------------------------------------------"
                                + "\nName: <b>"
                                + ((Web2GameCode) web2GameCode).getName()
                                + "</b>\n"
                                + "Id Item:: <b>"
                                + ((Web2GameCode) web2GameCode).getIdItem()
                                + "</b>\n"
                                +  "\nQuantity: <b>"
                                + ((Web2GameCode) web2GameCode).getQuantity()
                                + "</b>\n"
                                + "Game Name: <b>"
                                + ((Web2GameCode) web2GameCode).getGameName()
                                + "</b>\n"
                                + "Platform: <b>"
                                + ((Web2GameCode) web2GameCode).getPlatform()
                                + "</b>\n"
                                + "Added by: <b>"
                                + ((Web2GameCode) web2GameCode).getAddedBy()
                                + "</b>\n"
                                + "Fiat Price: <b>"
                                + ((Web2GameCode) web2GameCode).getUsdPrice()
                                + " USD</b>\n"
                                + "Img Path: <b>"
                                + ((Web2GameCode) web2GameCode).getImgPath()
                                + "</b>\n"
                                + "Redeem Code: <b>"
                                + ((Web2GameCode) web2GameCode).getRedeemCode()
                                + "</b>\n"
                                + "------------------------------------------------"
                        )
                        .photo(new InputFile(((Web2GameCode) web2GameCode).getImgPath()))
                        .parseMode("HTML")
                        .chatId(message.getCallbackQuery().getMessage().getChatId())
                        .build();
                sendPhotos.add(sendPhoto);
            }
        } else if (webItems.stream().anyMatch(webItem -> webItem instanceof Web2GiftCard)) {
            for (WebItem web2GiftCard: webItems) {
                SendPhoto sendPhoto = SendPhoto.builder()
                        .caption(  "------------------------------------------------"
                                + "\nName: <b>"
                                + ((Web2GiftCard) web2GiftCard).getName()
                                + "</b>\n"
                                + "Id Item: <b>"
                                + ((Web2GiftCard) web2GiftCard).getIdItem()
                                + "</b>\n"
                                +  "\nQuantity: <b>"
                                + ((Web2GiftCard) web2GiftCard).getQuantity()
                                + "</b>\n"
                                + "Platform: <b>"
                                + ((Web2GiftCard) web2GiftCard).getPlatform()
                                + "</b>\n"
                                + "Value USD: <b>"
                                + ((Web2GiftCard) web2GiftCard).getValueUsd()
                                + "</b>\n"
                                + "Added by: <b>"
                                + ((Web2GiftCard) web2GiftCard).getAddedBy()
                                + "</b>\n"
                                + "Fiat Price: <b>"
                                + ((Web2GiftCard) web2GiftCard).getUsdPrice()
                                + " USD</b>\n"
                                + "Img Path: <b>"
                                + ((Web2GiftCard) web2GiftCard).getImgPath()
                                + "</b>\n"
                                + "Redeem Code: <b>"
                                + ((Web2GiftCard) web2GiftCard).getRedeemCode()
                                + "</b>\n"
                                + "------------------------------------------------"
                        )
                        .photo(new InputFile(((Web2GiftCard) web2GiftCard).getImgPath()))
                        .parseMode("HTML")
                        .chatId(message.getCallbackQuery().getMessage().getChatId())
                        .build();
                sendPhotos.add(sendPhoto);
            }
        } else {
            for (WebItem web3NFT: webItems) {
                SendPhoto sendPhoto = SendPhoto.builder()
                        .caption(  "------------------------------------------------"
                                + "\nName: <b>"
                                + ((Web3NFT) web3NFT).getName()
                                + "</b>\n"
                                + "Id Item: <b>"
                                + ((Web3NFT) web3NFT).getIdItem()
                                + "</b>\n"
                                +  "\nQuantity: <b>"
                                + ((Web3NFT) web3NFT).getQuantity()
                                + "</b>\n"
                                + "Blockchain: <b>"
                                + ((Web3NFT) web3NFT).getBlockchain()
                                + "</b>\n"
                                + "Contract Address: <b>"
                                + ((Web3NFT) web3NFT).getContractAddress()
                                + "</b>\n"
                                + "Token Standard: <b>"
                                + ((Web3NFT) web3NFT).getTokenStandard()
                                + "</b>\n"
                                + "NFTTokenId: <b>"
                                + ((Web3NFT) web3NFT).getNFTTokenId()
                                + "</b>\n"
                                + "Quality: <b>"
                                + ((Web3NFT) web3NFT).getQuality()
                                + "</b>\n"
                                + "Added by: <b>"
                                + ((Web3NFT) web3NFT).getAddedBy()
                                + "</b>\n"
                                + "Crypto Price: <b>"
                                + ((Web3NFT) web3NFT).getCryptoPrice()
                                + " ETH</b>\n"
                                + "Img Path: <b>"
                                + ((Web3NFT) web3NFT).getImgPath()
                                + "</b>\n"
                                + "------------------------------------------------"
                        )
                        .photo(new InputFile(((Web3NFT) web3NFT).getImgPath()))
                        .parseMode("HTML")
                        .chatId(message.getCallbackQuery().getMessage().getChatId())
                        .build();
                sendPhotos.add(sendPhoto);
            }
        }
        return sendPhotos;
    }

}
