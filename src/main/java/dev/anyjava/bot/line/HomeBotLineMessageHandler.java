package dev.anyjava.bot.line;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

@Slf4j
@LineMessageHandler
public class HomeBotLineMessageHandler {

    private final String key;

    public HomeBotLineMessageHandler(@Value("${line.bot.channelToken:}") String key) {
        this.key = key;
    }

    @PostConstruct
    public void init() {
        log.info("STARTED LINE BOT!, keySize={}", key.length());
    }

    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<MessageContent> event) {
        log.info("event: {}", event);
        if (accept(event)) {
            return TextMessage.builder().text("OK").build();
        }
        return null; // no reply
    }

    private boolean accept(MessageEvent<MessageContent> event) {
        MessageContent messageContent = event.getMessage();
        return messageContent instanceof TextMessageContent &&
                (((TextMessageContent) messageContent).getText().startsWith(".") ||
                        ((TextMessageContent) messageContent).getText().startsWith("/"));
    }
}