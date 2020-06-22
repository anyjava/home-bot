package dev.anyjava.bot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "telegram")
public class TelegramBotConfig {

    private String token;
    private String botName;
    private Long managerChatId;
}
