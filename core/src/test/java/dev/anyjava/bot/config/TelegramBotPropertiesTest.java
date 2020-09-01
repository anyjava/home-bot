package dev.anyjava.bot.config;

import dev.anyjava.bot.support.TestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class TelegramBotPropertiesTest extends TestSupport {

    @Autowired
    TelegramBotProperties telegramBotProperties;

    @Test
    void testToken() {
        assertThat(telegramBotProperties.getToken()).isEqualTo("tttt");
        assertThat(telegramBotProperties.getBotName()).isEqualTo("ttttName");
    }
}