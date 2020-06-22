package dev.anyjava.bot.telegram;

import dev.anyjava.bot.config.TelegramBotConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@RequiredArgsConstructor
@Component
@Slf4j
public class TelegramNotiService {

    private final BotSender botSender;
    private final TelegramBotConfig telegramBotConfig;

    public void sendToManager(String messsage) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(telegramBotConfig.getManagerChatId());
        sendMessage.setText(messsage);

        try {
            botSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("failed to send telegram, error message={}", e.getMessage(), e);
        }
    }
}
