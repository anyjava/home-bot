package dev.anyjava.bot.line;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LineNotiService {

    private final String anyjavaId;
    private final LineMessageBroker lineMessageBroker;

    public LineNotiService(@Value("${anyjava.id:}") String anyjavaId,
                           LineMessageBroker lineMessageBroker) {
        this.anyjavaId = anyjavaId;
        this.lineMessageBroker = lineMessageBroker;
    }

    public void sendToAdmin(String message) {
        lineMessageBroker.pushMessage(anyjavaId, message);
    }

    public void sendToGroupChat(String message) {
        lineMessageBroker.pushMessage(message);
    }
}
