package dev.anyjava.bot;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class YourBotNameTest {

//    @Autowired
    YourBotName yourBotName;

//    @Autowired
    BotSender sender;

    @Ignore("텔레그렘은 일단 무시")
    @Test
    public void test() throws TelegramApiException {
        assertThat(yourBotName).isNotNull();

        SendMessage sn = new SendMessage();
        sn.setChatId(62241958L);
        sn.setText("test send");
        sender.execute(sn);
    }
}