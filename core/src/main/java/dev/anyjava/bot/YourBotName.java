package dev.anyjava.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

//Standard Spring component annotation
@Slf4j
//@Component
public class YourBotName extends TelegramLongPollingBot {

  @Override
  public void onUpdateReceived(Update update) {
      log.info("update = {}", update);
  }

  @Override
  public String getBotUsername() {
    return "";
  }

  @Override
  public String getBotToken() {
    return "";
  }
  //Bot body.
}