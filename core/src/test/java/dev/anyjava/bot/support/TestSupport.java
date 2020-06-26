package dev.anyjava.bot.support;

import dev.anyjava.bot.HomeBotApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = HomeBotApplication.class,
        properties = {"line.channelToken=test",
                "line.channelSecret=test",
                "tele.token=tttt",
                "tele.botname=ttttName",
                "tele.managerChatId=111",
                "home.bot.downloadPath=/var/tmp",
                "to=test"})
public abstract class TestSupport {
}
