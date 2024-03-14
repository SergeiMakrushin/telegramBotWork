package com.makrushin.telegrambot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
public class ExchangeRatesBot extends TelegramLongPollingBot {
    public ExchangeRatesBot(@Value("${bot.token}") String botToken) {
        super(botToken);
    }


    //    вызывается всякий раз, когда пользователь отправляет в бот сообщение. В этом методе я и буду обрабатывать поступающие от пользователя команды.
    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getFrom().getFirstName() + ", привет! Мы получили твое сообщение, скоро ответим";
        sendText(update.getMessage().getChatId(), message);
        System.out.println("Пользователь " + update.getMessage().getFrom().getFirstName()
                + " "
                + update.getMessage().getChatId()
                + " Отправил команду:  " + update.getMessage().getText());
    }
//отправляет сообщение пользователю
    public void sendText(Long id, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(id);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
//отправляет сообщение пользователю через контроллер
    public String getText(Long id, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(id);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        return message;
    }

    //    должен возвращать название бота, которое тоже можно поместить в проперти.
    @Override
    public String getBotUsername() {
        return "mak_s_work_bot";
    }


}
