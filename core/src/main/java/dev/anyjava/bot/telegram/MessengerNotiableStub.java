package dev.anyjava.bot.telegram;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessengerNotiableStub implements MessengerNotiable {
    @Override
    public void sendToManager(String message) {
        log.info("send to message stub, message={}", message);
    }
}
