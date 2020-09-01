package dev.anyjava.bot.telegram;

import dev.anyjava.bot.config.TelegramBotProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@RequiredArgsConstructor
@Slf4j
public class TelegramNotiService implements MessengerNotiable {

    private final BotSender botSender;
    private final TelegramBotProperties telegramBotProperties;

    @Override
    public void sendToManager(String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(telegramBotProperties.getManagerChatId());
        sendMessage.setText(message);

        try {
            botSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("failed to send telegram, error message={}", e.getMessage(), e);
        }
    }
}
