package dev.anyjava.bot.config;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

@Slf4j
@LineMessageHandler
public class EchoApplication {

    @Value("${line.bot.channelToken:}")
    private String key;

    @PostConstruct
    public void init() {
        log.info("STARTED LINE BOT!, keySize={}", key.length());
    }

    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws Exception {
        log.info("event: {}", event);
        return new TextMessage(event.getMessage().getText());
    }
}