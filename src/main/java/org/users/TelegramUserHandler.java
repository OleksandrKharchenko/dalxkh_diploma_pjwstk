package org.users;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegramrobot.MessageFlowGenerator;

public class TelegramUserHandler {

    public SendMessage addUserStartFlow(Message message){
        if(!TelegramClientUserService.verifyIfExists(Math.toIntExact(message.getFrom().getId()))){
            TelegramClientUserService.addTelegramClientUser(Math.toIntExact(message.getFrom().getId()),
                    message.getFrom().getFirstName());
        }
        MessageFlowGenerator messageFlowGenerator = new MessageFlowGenerator();
        SendMessage sm = messageFlowGenerator.startFlow(message.getChatId().toString());
        return sm;
    }
}
