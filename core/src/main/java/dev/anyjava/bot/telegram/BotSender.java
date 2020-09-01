package dev.anyjava.bot.telegram;

import dev.anyjava.bot.config.TelegramBotProperties;
import lombok.Setter;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;

public class BotSender extends DefaultAbsSender {

    @Setter
    TelegramBotProperties telegramBotProperties;

    public BotSender() {
        super(new DefaultBotOptions());
    }

    @Override
    public String getBotToken() {
        return telegramBotProperties.getToken();
    }
}
