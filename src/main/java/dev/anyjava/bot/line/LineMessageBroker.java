package dev.anyjava.bot.line;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Slf4j
@RequiredArgsConstructor
@Component
public class LineMessageBroker {

    private final LineMessagingClient lineMessagingClient;

    @Value("${to}")
    private String to;

    public void pushMessage(String message) {
        pushMessage(this.to, message);
    }

    public void pushMessage(String to, String message) {
        final TextMessage textMessage = new TextMessage(message);
        final PushMessage pushMessage = new PushMessage(
                to,
                textMessage);

        final BotApiResponse botApiResponse;
        try {
            botApiResponse = lineMessagingClient.pushMessage(pushMessage).get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("failed push message to={}, message={}", to, message);
            return;
        }
        log.info("RESPONSE = {}", botApiResponse);
    }
}
