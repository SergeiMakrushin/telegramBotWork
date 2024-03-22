package com.makrushin.telegrambot;

import com.makrushin.telegrambot.bot.ExchangeRatesBot;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
@OpenAPIDefinition
@EnableScheduling
@SpringBootApplication
public class TelegrambotApplication {
    public static void main(String[] args) throws TelegramApiException {
        SpringApplication.run(TelegrambotApplication.class, args);

    }
}
