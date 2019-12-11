package com.telegram.bot;

import com.telegram.entity.Info;
import com.telegram.service.CityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Component
public class Bot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String botToken;

    private static final String CITY_NOT_FOUND_MESSAGE = "нет такого города";
    private static final String NO_INFO_MESSAGE = "по данному городу нет никакой информации";

    private final CityInfoService cityInfoService;

    @Autowired
    public Bot(CityInfoService cityInfoService) {
        this.cityInfoService = cityInfoService;
    }

    public void onUpdateReceived(Update update) {
        try {
            Message message = update.getMessage();
            SendMessage sendMessage = new SendMessage();
            if (message != null && message.hasText()) {
                sendMessage.setText(createOneStringInfoForCity(message.getText()));
            } else {
                sendMessage.setText(CITY_NOT_FOUND_MESSAGE);
            }
            sendMessage.setChatId(update.getMessage().getChatId());
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private String createOneStringInfoForCity(String cityName) {
        try {
            List<Info> allInfo = cityInfoService.findAllInfoForCity(cityName);
            if (allInfo.isEmpty()) {
                return NO_INFO_MESSAGE;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (Info info: allInfo) {
                stringBuilder.append(info.getInfoMessage());
                stringBuilder.append(". ");
            }
            return stringBuilder.toString();
        } catch (EntityNotFoundException e) {
            return CITY_NOT_FOUND_MESSAGE;
        }
    }

    public String getBotUsername() {
        return botName;
    }

    public String getBotToken() {
        return botToken;
    }
}
