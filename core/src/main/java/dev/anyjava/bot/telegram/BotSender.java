package dev.anyjava.bot.telegram;

import dev.anyjava.bot.config.TelegramBotConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Component
public class BotSender extends DefaultAbsSender {

    @Autowired
    TelegramBotConfig telegramBotConfig;

    protected BotSender() {
        super(new DefaultBotOptions());
    }

    @Override
    public String getBotToken() {
        return telegramBotConfig.getToken();
    }
}
