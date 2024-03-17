package com.makrushin.telegrambot.bot;

import com.makrushin.telegrambot.model.NotificationTask;
import com.makrushin.telegrambot.repositiry.NotificationTaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ExchangeRatesBot extends TelegramLongPollingBot {

    Logger logger = LoggerFactory.getLogger(ExchangeRatesBot.class);

    public ExchangeRatesBot(@Value("${bot.token}") String botToken) {
        super(botToken);
    }

    @Autowired
    private NotificationTaskRepository notificationTaskRepository;

    private final String SAMPLE = " 01.01.2022 20:00 Сделать домашнюю работу";


    // Метод вызывается всякий раз, когда пользователь отправляет в бот сообщение.
    // В этом методе я и буду обрабатывать поступающие от пользователя команды.
    @Override
    public void onUpdateReceived(Update update) {
        logger.info("метод получения и обработки сообщения");

        String message = update.getMessage().getText();
        Long id = update.getMessage().getChatId();
        String name = update.getMessage().getFrom().getFirstName();
        String startMessage = "Привет, " + name + "! отправь сообщение по образцу:" + SAMPLE;
        //проверка на команду start
        if (message.equals("/start")||message.equals("start")) {
            sendText(id, startMessage);
            return;
        }
        //проверка сообщения на соответствие паттерну и передача для дальнейшей обработки
        String strPattern = "([0-9\\.\\:\\s]{16})(\\s)([\\W+]+)";
        Pattern pattern = Pattern.compile(strPattern);
        LocalDateTime dateMessage = LocalDateTime.now();
        Matcher matcher = pattern.matcher(message);

        if (matcher.matches()) {
            String date = matcher.group(1);
            dateMessage = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        }

        saveRepository(id, name, message, dateMessage);
    }

    // Метод создает объект из данных сообщения и передает в базу данных
    public NotificationTask saveRepository(Long id, String name, String message, LocalDateTime dateMessage) {
        logger.info("метод создания объекта и передачи в базу данных");

        NotificationTask notificationTask = new NotificationTask();
        notificationTask.setId(id);
        notificationTask.setUserId(id);
        notificationTask.setName(name);
        notificationTask.setMessage(message);
        notificationTask.setTimeMessage(dateMessage);

        return notificationTaskRepository.save(notificationTask);
    }

    // Метод поиска сообщений в базе по дате и отправкой сообщений при совпадении
    @Scheduled(cron = "0 * * * * *")
    public List<NotificationTask> findTaskByTime() {
        logger.info("метод поиска, раз в минуту");
        //    округляем дату до минут
        LocalDateTime newTime = LocalDateTime.now().plusMinutes(0).withSecond(0).withNano(0);

        List<NotificationTask> taskList = notificationTaskRepository.findNotificationTaskByTimeMessage(newTime);
        //передаем в метод для отправки сообщений полученные объекты
        taskList.stream()
                .forEach(s -> sendText(s.getUserId(), s.getMessage()));

        return notificationTaskRepository.findNotificationTaskByTimeMessage(newTime);
    }

    // Метод отправляет сообщение пользователю
    public void sendText(Long id, String message) {
        logger.info("метод отправки сообщения пользователю");

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(id);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            logger.error("ошибка отправки сообщения");
        }
    }


    @Override
    public String getBotUsername() {
        return "mak_s_work_bot";
    }

    // Метод отправляет сообщение пользователю через контроллер
    public String getText(Long id, String message) {
        logger.info("метод отправки сообщения пользователю, через controller");

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(id);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
             logger.error("ошибка отправки сообщения");
        }

        return message;
    }


}
