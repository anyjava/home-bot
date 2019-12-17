package dev.anyjava.bot.line;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.source.UserSource;
import com.linecorp.bot.model.message.TextMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class HomeBotLineMessageHandlerTest {

    private HomeBotLineMessageHandler messageHandler;

    @BeforeEach
    public void setUpObject() {
        messageHandler = new HomeBotLineMessageHandler("", stockQueryService, stockMessageBuilder);
    }

    @DisplayName(". 로 시작하면 파싱대상의 메시지임")
    @ParameterizedTest
    @ValueSource(strings = {".sdfasd", "/asdfasd"})
    public void testParsingAccept(String textValue) {
        MessageContent message = new TextMessageContent("", textValue);
        MessageEvent<MessageContent> mockMessage = new MessageEvent("", new UserSource(""), message, Instant.MAX);

        TextMessage textMessage = messageHandler.handleTextMessageEvent(mockMessage);

        assertThat(textMessage).isNotNull();
    }
}