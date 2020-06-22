package dev.anyjava.bot.config;

import dev.anyjava.bot.support.TestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class TelegramBotConfigTest extends TestSupport {

    @Autowired
    TelegramBotConfig telegramBotConfig;

    @Test
    void testToken() {
        assertThat(telegramBotConfig.getToken()).isEqualTo("tttt");
        assertThat(telegramBotConfig.getBotName()).isEqualTo("ttttName");
    }
}