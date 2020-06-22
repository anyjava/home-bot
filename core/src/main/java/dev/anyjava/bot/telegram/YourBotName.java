package dev.anyjava.bot.telegram;

import dev.anyjava.bot.config.TelegramBotConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

//Standard Spring component annotation
@Slf4j
@Component
public class YourBotName extends TelegramLongPollingBot {

  @Autowired
  TelegramBotConfig telegramBotConfig;

  @Override
  public void onUpdateReceived(Update update) {
      log.info("update = {}", update);
  }

  @Override
  public String getBotUsername() {
    return telegramBotConfig.getBotName();
  }

  @Override
  public String getBotToken() {
    return telegramBotConfig.getToken();
  }
  //Bot body.
}