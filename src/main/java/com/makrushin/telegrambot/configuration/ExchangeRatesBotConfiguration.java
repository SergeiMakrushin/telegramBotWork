package com.makrushin.telegrambot.configuration;

import com.makrushin.telegrambot.bot.ExchangeRatesBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class ExchangeRatesBotConfiguration  {
//создали клас который будет обрабатывать команды от нашего телеграмм бота, создаем бин, сообщаем библиотеке
    @Bean
    public TelegramBotsApi telegramBotsApi(ExchangeRatesBot exchangeRatesBot) throws TelegramApiException {
//        регистрируем ExchangeRatesBot exchangeRatesBot в классе
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(exchangeRatesBot);
        return api;
    }
//    public static void main(String[] args) throws TelegramApiException {
//        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//        botsApi.registerBot(new Bot());
}
