package dev.anyjava.bot.config;

import dev.anyjava.bot.telegram.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

@Configuration
public class TelegramBotConfig {

    @Profile("!test")
    @Bean
    public MessengerNotiable botSenderAdapter(TelegramBotProperties telegramBotProperties) {
        BotSender botSender = new BotSender();
        botSender.setTelegramBotProperties(telegramBotProperties);

        return new TelegramNotiService(botSender, telegramBotProperties);
    }

    @Profile("!test")
    @Bean
    public TelegramLongPollingBot yourBotName(TelegramBotProperties telegramBotProperties) {
        return new YourBotName(telegramBotProperties);
    }

    @Profile("test")
    @Bean
    public MessengerNotiable stub() {
        return new MessengerNotiableStub();
    }

}
