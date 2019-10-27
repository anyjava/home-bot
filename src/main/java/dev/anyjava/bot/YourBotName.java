package dev.anyjava.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

//Standard Spring component annotation
@Component
public class YourBotName extends TelegramLongPollingBot {

  @Override
  public void onUpdateReceived(Update update) {

  }

  @Override
  public String getBotUsername() {
    return null;
  }

  @Override
  public String getBotToken() {
    return null;
  }
  //Bot body.
}