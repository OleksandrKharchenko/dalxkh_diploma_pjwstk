package org.telegramrobot;

import org.orders.TelegramOrderControllerHandler;
import org.payments.TelegramPaymentControllerHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerPreCheckoutQuery;
import org.telegram.telegrambots.meta.api.methods.invoices.SendInvoice;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.users.TelegramClientUser;
import org.users.TelegramClientUserService;
import org.users.TelegramOperationalUserControllerHandler;
import org.users.TelegramUserControllerHandler;
import org.webitems.*;

import java.util.List;

public class InetItemStoreBotAdmin extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return EnvTelegramBotsVars.BOTUSERNAMEADMIN;
    }

    @Override
    public String getBotToken() {
        return EnvTelegramBotsVars.TELEGRAMTOKENAPIADMIN;
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    @Override
    public void onUpdateReceived(Update update) {
        //OPS USER CHECKER
        TelegramOperationalUserControllerHandler telegramOperationalUserControllerHandler = new TelegramOperationalUserControllerHandler();
        if(update.hasMessage() && telegramOperationalUserControllerHandler.isOperational(update.getMessage().getFrom().getId())
                || update.hasCallbackQuery() && telegramOperationalUserControllerHandler.isOperational(update.getCallbackQuery().getFrom().getId())) {
            if (update.hasMessage() && update.getMessage().hasText()) {
                //COMMANDS
                if (update.getMessage().isCommand()) {
                    if (update.getMessage().getText().equals("/start")) {
                        MessageFlowGenerator messageFlowGenerator = new MessageFlowGenerator();
                        SendMessage sm = messageFlowGenerator.startFlowAdmin(update.getMessage().getChatId());
                        try {
                            execute(sm);
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    } else if (update.getMessage().getText().equals("/ban_users")) {
                        if (telegramOperationalUserControllerHandler.isSuperUser(update.getMessage().getFrom().getId())){
                            List<SendMessage> sendMessageList = telegramOperationalUserControllerHandler.getTelegramClientBanUsers(update);
                            for (SendMessage sm : sendMessageList) {
                                try {
                                    execute(sm);
                                } catch (TelegramApiException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        } else {
                            SendMessage sm = SendMessage.builder()
                                    .text("You are not Super User. Access to /ban_users module disabled.")
                                    .chatId(update.getMessage().getChatId())
                                    .build();
                            try {
                                execute(sm);
                            } catch (TelegramApiException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } else if (update.getMessage().getText().equals("/unban_users")) {
                        if (telegramOperationalUserControllerHandler.isSuperUser(update.getMessage().getFrom().getId())){
                        List<SendMessage> sendMessageList = telegramOperationalUserControllerHandler.getTelegramClientUnBanUsers(update);
                        for (SendMessage sm : sendMessageList) {
                            try {
                                execute(sm);
                            } catch (TelegramApiException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        } else {
                            SendMessage sm = SendMessage.builder()
                                    .text("You are not Super User. Access to /ban_users module disabled.")
                                    .chatId(update.getMessage().getChatId())
                                    .build();
                            try {
                                execute(sm);
                            } catch (TelegramApiException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } else if (update.getMessage().getText().equals("/promote_user")) {
                        if (telegramOperationalUserControllerHandler.isSuperUser(update.getMessage().getFrom().getId())){
                        List<SendMessage> sendMessageList = telegramOperationalUserControllerHandler.getTelegramClientPromoteUser(update);
                        for (SendMessage sm : sendMessageList) {
                            try {
                                execute(sm);
                            } catch (TelegramApiException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        } else {
                            SendMessage sm = SendMessage.builder()
                                    .text("You are not Super User. Access to /ban_users module disabled.")
                                    .chatId(update.getMessage().getChatId())
                                    .build();
                            try {
                                execute(sm);
                            } catch (TelegramApiException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } else if (update.getMessage().getText().equals("/dissolve_opsuser")) {
                        if (telegramOperationalUserControllerHandler.isSuperUser(update.getMessage().getFrom().getId())){
                        List<SendMessage> sendMessageList = telegramOperationalUserControllerHandler.getTelegramDissolveOperUser(update);
                        for (SendMessage sm : sendMessageList) {
                            try {
                                execute(sm);
                            } catch (TelegramApiException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        } else {
                            SendMessage sm = SendMessage.builder()
                                    .text("You are not Super User. Access to /ban_users module disabled.")
                                    .chatId(update.getMessage().getChatId())
                                    .build();
                            try {
                                execute(sm);
                            } catch (TelegramApiException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } else if (update.getMessage().getText().equals("/whoami")) {
                        SendMessage sm = telegramOperationalUserControllerHandler.whoami(update);
                        try {
                            execute(sm);
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    } else if (update.getMessage().getText().equals("/get_orders")) {
                        SendMessage sm = telegramOperationalUserControllerHandler.getStateOfOrders(update);
                        try {
                            execute(sm);
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    } else if (update.getMessage().getText().equals("/del_order")) {
                        List<SendMessage> sendMessageList = telegramOperationalUserControllerHandler.getAllOrders(update);
                        for (SendMessage sm : sendMessageList) {
                            try {
                                execute(sm);
                            } catch (TelegramApiException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
            //CALLBACK QUERIES
            if (update.hasCallbackQuery()) {

                if (update.getCallbackQuery().getData().substring(0, 6).equals("banUsr")) {
                    SendMessage sm = telegramOperationalUserControllerHandler.banTelegramClientUser(update);
                    try {
                        execute(sm);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                } else if (update.getCallbackQuery().getData().substring(0, 8).equals("unbanUsr")) {
                    SendMessage sm = telegramOperationalUserControllerHandler.unbanTelegramClientUser(update);
                    try {
                        execute(sm);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                } else if (update.getCallbackQuery().getData().substring(0, 7).equals("promote")) {
                    SendMessage sm = telegramOperationalUserControllerHandler.promoteTelegramClientUser(update);
                    try {
                        execute(sm);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }  else if (update.getCallbackQuery().getData().substring(0, 8).equals("dissolve")) {
                    SendMessage sm = telegramOperationalUserControllerHandler.dissolveTelegramOperUser(update);
                    try {
                        execute(sm);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                } else if (update.getCallbackQuery().getData().equals("completed")) {
                    System.out.println("COMPLETED");
                    List<SendMessage> sendMessageList = telegramOperationalUserControllerHandler.getOrders(update, "completed");
                    for (SendMessage sm : sendMessageList) {
                        try {
                            execute(sm);
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else if (update.getCallbackQuery().getData().equals("canceled")) {
                    List<SendMessage> sendMessageList = telegramOperationalUserControllerHandler.getOrders(update, "canceled");
                    for (SendMessage sm : sendMessageList) {
                        try {
                            execute(sm);
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else if (update.getCallbackQuery().getData().equals("payment")) {
                    List<SendMessage> sendMessageList = telegramOperationalUserControllerHandler.getOrders(update, "payment");
                    for (SendMessage sm : sendMessageList) {
                        try {
                            execute(sm);
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else if (update.getCallbackQuery().getData().equals("initiated")) {
                    List<SendMessage> sendMessageList = telegramOperationalUserControllerHandler.getOrders(update, "initiated");
                    for (SendMessage sm : sendMessageList) {
                        try {
                            execute(sm);
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else if (update.getCallbackQuery().getData().substring(0, 6).equals("delete")) {
                    SendMessage sm = telegramOperationalUserControllerHandler.deleteOrder(update);
                    try {
                        execute(sm);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            }




















        } else {
            SendMessage sendMessage;
            sendMessage = SendMessage.builder()
                    .text("You are not allowed to use this bot.")
                    .chatId(update.getMessage().getChatId())
                    .build();
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }


    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }
}
