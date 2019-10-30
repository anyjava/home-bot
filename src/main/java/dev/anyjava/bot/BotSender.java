package dev.anyjava.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Component
public class BotSender extends DefaultAbsSender {

    protected BotSender() {
        super(new DefaultBotOptions());
    }

    @Override
    public String getBotToken() {
        return "";

    }
}
