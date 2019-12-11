package com.telegram;

import com.telegram.bot.Bot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		try {
			telegramBotsApi.registerBot(context.getBean(Bot.class));
		} catch (TelegramApiRequestException e) {
			e.printStackTrace();
		}
	}

}
