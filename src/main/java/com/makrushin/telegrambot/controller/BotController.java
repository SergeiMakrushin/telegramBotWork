package com.makrushin.telegrambot.controller;

import com.makrushin.telegrambot.bot.ExchangeRatesBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

@RestController
@RequestMapping("/bot")
public class BotController {
    @Autowired
    ExchangeRatesBot exchangeRatesBot;

    // Метод отправляет сообщение пользователю через swagger
    @GetMapping
    public String botMessage(@RequestParam("id") Long id, @RequestParam("message") String message) {
        return exchangeRatesBot.getText(id, message);
    }
}
