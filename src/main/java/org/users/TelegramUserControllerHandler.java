package org.users;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramrobot.MessageFlowGenerator;

public class TelegramUserControllerHandler {

    public SendMessage addUserStartFlow(Message message){
        TelegramClientUserService telegramClientUserService = new TelegramClientUserService();
        if(!telegramClientUserService.verifyIfExists(Math.toIntExact(message.getFrom().getId()))){
            telegramClientUserService.addTelegramClientUser(Math.toIntExact(message.getFrom().getId()),
                    message.getFrom().getFirstName());
        }
        MessageFlowGenerator messageFlowGenerator = new MessageFlowGenerator();
        SendMessage sm = messageFlowGenerator.startFlow(message.getChatId().toString());
        return sm;
    }

    public boolean verifyCryptoAddress(Update update){
        TelegramClientUserService telegramClientUserService = new TelegramClientUserService();
        TelegramClientUser telegramClientUser = telegramClientUserService.getTelegramClientUser(Math.toIntExact(update.getMessage().getFrom().getId()));
        if (telegramClientUser.getCyptoWalletAdress() != null){
           return true;
        }
        return false;
    }

    public boolean verifyEmailAddress(Update update){
        TelegramClientUserService telegramClientUserService = new TelegramClientUserService();
        TelegramClientUser telegramClientUser = telegramClientUserService.getTelegramClientUser(Math.toIntExact(update.getMessage().getFrom().getId()));
        if (telegramClientUser.getEmail() != null){
            return true;
        }
        return false;
    }

    public SendMessage setCryptoAddress(Update update){
        TelegramClientUserService telegramClientUserService = new TelegramClientUserService();
        TelegramClientUser telegramClientUser = telegramClientUserService.getTelegramClientUser(Math.toIntExact(update.getMessage().getFrom().getId()));
        SendMessage sm;
        telegramClientUser.setCyptoWalletAdress(update.getMessage().getText().trim());
        telegramClientUserService.updateTelegramClientUser(telegramClientUser);
        sm = SendMessage.builder()
                .text("Crypto address: <b>" + update.getMessage().getText().trim() + "</b> successfully set up. You can buy NFT now.")
                .parseMode("HTML")
                .chatId(update.getMessage().getChatId())
                .build();
        return sm;
    }

    public SendMessage prepareUpdateCryptoAddress(Update update){
        SendMessage sm = SendMessage.builder()
                .text("In order to update your crypto address in our database, please, reply with your crypto address to message above \uD83D\uDC46")
                .parseMode("HTML")
                .chatId(update.getMessage().getChatId())
                .build();
        return sm;
    }

    public SendMessage setEmailAddress(Update update){
        TelegramClientUserService telegramClientUserService = new TelegramClientUserService();
        TelegramClientUser telegramClientUser = telegramClientUserService.getTelegramClientUser(Math.toIntExact(update.getMessage().getFrom().getId()));
        SendMessage sm;
        telegramClientUser.setEmail(update.getMessage().getText().trim());
        telegramClientUserService.updateTelegramClientUser(telegramClientUser);
        sm = SendMessage.builder()
                .text("Email address: <b>" + update.getMessage().getText().trim() + "</b> successfully set up. You can buy Game Codes and Gift Cards now.")
                .parseMode("HTML")
                .chatId(update.getMessage().getChatId())
                .build();
        return sm;
    }

    public SendMessage prepareUpdateEmailAddress(Update update){
        SendMessage sm = SendMessage.builder()
                .text("In order to update your email address in our database, please, reply with your email address to message above \uD83D\uDC46")
                .parseMode("HTML")
                .chatId(update.getMessage().getChatId())
                .build();
        return sm;
    }

}
