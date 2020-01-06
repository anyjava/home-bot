package dev.anyjava.bot.line;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import dev.anyjava.bot.stock.service.StockMessageBuilder;
import dev.anyjava.bot.stock.service.StockQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Slf4j
@Profile({"!local"})
@LineMessageHandler
public class HomeBotLineMessageHandler {

    private final String key;
    private final StockQueryService stockQueryService;
    private final StockMessageBuilder stockMessageBuilder;

    public HomeBotLineMessageHandler(@Value("${line.bot.channelToken:}") String key,
                                     StockQueryService stockQueryService,
                                     StockMessageBuilder stockMessageBuilder) {
        this.key = key;
        this.stockQueryService = stockQueryService;
        this.stockMessageBuilder = stockMessageBuilder;
    }

    @PostConstruct
    public void init() {
        log.info("STARTED LINE BOT!, keySize={}", key.length());
    }

    @EventMapping
    public Message handleTextMessageEvent(MessageEvent<MessageContent> event) {
        log.info("event: {}", event);
        if (accept(event)) {
            // TODO: 07/01/2020 중복제거
            return stockQueryService.findByName("카카오")
                    .map(stockMessageBuilder::build)
                    .map(this::buildTextMessage)
                    .orElse(null);
        }
        return null; // no reply
    }

    private TextMessage buildTextMessage(String message) {
        return TextMessage.builder()
                .text(message)
                .build();
    }

    private boolean accept(MessageEvent<MessageContent> event) {
        MessageContent messageContent = event.getMessage();
        return messageContent instanceof TextMessageContent &&
                (((TextMessageContent) messageContent).getText().startsWith(".") ||
                        ((TextMessageContent) messageContent).getText().startsWith("/"));
    }
}