package com.makrushin.telegrambot.bot;

import com.makrushin.telegrambot.model.NotificationTask;
import com.makrushin.telegrambot.model.SaveFile;
import com.makrushin.telegrambot.repository.NotificationTaskRepository;
import com.makrushin.telegrambot.repository.SaveFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ExchangeRatesBot extends TelegramLongPollingBot {

    private Logger logger = LoggerFactory.getLogger(ExchangeRatesBot.class);
//    @Value("${bot.token}")
//    private String botToken;

    public ExchangeRatesBot(@Value("${bot.token}") String botToken) {
        super(botToken);
    }

    @Value("${bot.name}")
    private String nameBot;


    @Autowired
    private NotificationTaskRepository notificationTaskRepository;
@Autowired
   private SaveFileRepository saveFileRepository;
    private final String SAMPLE = " 01.01.2022 20:00 Сделать домашнюю работу";


    // Метод вызывается всякий раз, когда пользователь отправляет в бот сообщение.
    // В этом методе я и буду обрабатывать поступающие от пользователя команды.
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            Message message = update.getMessage();
//            Chat chat = message.getChat();

            if (message.hasPhoto()) {
//int numberPhoto=message.getPhoto().size();
//                List<PhotoSize> photos = message.getPhoto();
                PhotoSize photo = update.getMessage().getPhoto().get(message.getPhoto().size() - 1);

//                отправляем его же фото обратно
                String f_id = photo.getFileId();
                int f_width = photo.getWidth();
                int f_height = photo.getHeight();
                String caption = "file_id: " + f_id + "\nwidth: " + Integer.toString(f_width) + "\nheight: " + Integer.toString(f_height);
                String fId = photo.getFileId();
                long chatId = update.getMessage().getChatId();
                SendPhoto msg = SendPhoto
                        .builder()
                        .chatId(chatId)
                        .photo(new InputFile(fId))
//                        .caption(caption)
                        .build();
                try {
                    execute(msg); // Call method to send the photo with caption
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

//                        https://api.telegram.org/bot<token>/getFile?file_id={fileId}
//                        https://api.telegram.org/file/bot<token>/<file_path>

                GetFile getFile = new GetFile(photo.getFileId());
                    try {
                        File file = execute(getFile); //tg file obj
                        System.out.println("file = " + file);
                        System.out.println("file.getFilePath() = " + file.getFilePath());


//                        получение массива байт
                        java.io.File file1=downloadFile(file.getFilePath());

                        byte[] bytes = new byte[(int) file1.length()];
                        FileInputStream fis = null;
                        try {
                            fis = new FileInputStream(file1);
                            fis.read(bytes);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } finally {
                            if (fis != null) {
                                fis.close();
                            }
                        }
                        System.out.println("bytes = " + bytes);

                        SaveFile saveFile=new SaveFile(1,bytes);
                        saveFileRepository.save(saveFile);
                    } catch (TelegramApiException | IOException e) {
                        e.printStackTrace();
                    }


//                сохранение файла в директорию
//                GetFile getFile = new GetFile(photo.getFileId());
//                    try {
//                        File file = execute(getFile); //tg file obj
//                        System.out.println("file = " + file);
//                        System.out.println("file.getFilePath() = " + file.getFilePath());
//                        https://api.telegram.org/bot<token>/getFile?file_id={fileId}
//                        https://api.telegram.org/file/bot<token>/<file_path>
////                        downloadFile(file, new java.io.File("photos/photo"+ ".jpg"));
//                       downloadFile(file, new java.io.File("photos/photo"+ ".jpg"));
//
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                вариант с циклом
//                int count = 1;
//                for (PhotoSize photo : photos) {
//                    GetFile getFile = new GetFile(photo.getFileId());
//                    try {
//                        File file = execute(getFile); //tg file obj
//                        downloadFile(file, new java.io.File("photos/photo" + count + ".png"));
//                        count++;
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
            }
        }


        logger.info("метод получения и обработки сообщения" + update);
//        String message="@"+update.getMessage().getFrom().getUserName();
        System.out.println("update.getMessage().getPhoto().size() = " + update.getMessage().getPhoto().size());
//        MultipartFile file=update.getMessage().getPhoto().stream().findFirst().orElseThrow();
//        File f_path = new File("https://api.telegram.org/file/bot"+getBotToken()+"/"+file.getFilePath());


        long id = 5286947855l;
//        sendText(id, message);
//        String phone = update.getMessage().getContact().getPhoneNumber();
//        sendText(update.getMessage().getChatId(), "Привет, мы сейчас очень заняты, поговорим потом или напиши " + phone);

//        String message = update.getMessage().getText();
//        Long id = update.getMessage().getChatId();
//        String name = update.getMessage().getFrom().getFirstName();
//        String startMessage = "Привет, " + name + "! отправь сообщение по образцу:" + SAMPLE;
//        //проверка на команду start
//        if (message.equals("/start") || message.equals("start")) {
//            sendText(id, startMessage);
//            return;
//        }
//        //проверка сообщения на соответствие паттерну и передача для дальнейшей обработки
//        String strPattern = "([0-9\\.\\:\\s]{16})(\\s)([\\W+]+)";
//        Pattern pattern = Pattern.compile(strPattern);
//        LocalDateTime dateMessage = LocalDateTime.now();
//        Matcher matcher = pattern.matcher(message);
//
//        if (matcher.matches()) {
//            String date = matcher.group(1);
//            dateMessage = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
//        }
//
//        saveRepository(id, name, message, dateMessage);
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
                .forEach(notificationTask -> sendText(notificationTask.getUserId(), notificationTask.getMessage()));

        return notificationTaskRepository.findNotificationTaskByTimeMessage(newTime);
    }

    // Метод отправляет сообщение пользователю
    public String sendText(Long id, String message) {
        logger.info("метод отправки сообщения пользователю");

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


    @Override
    public String getBotUsername() {
        return nameBot;
    }


}
