package dev.anyjava.bot.telegram;

import dev.anyjava.bot.config.TelegramBotProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

//Standard Spring component annotation
@Slf4j
@RequiredArgsConstructor
public class YourBotName extends TelegramLongPollingBot {

  private final TelegramBotProperties telegramBotProperties;

  @Override
  public void onUpdateReceived(Update update) {
      log.info("update = {}", update);
  }

  @Override
  public String getBotUsername() {
    return telegramBotProperties.getBotName();
  }

  @Override
  public String getBotToken() {
    return telegramBotProperties.getToken();
  }
  //Bot body.
}