package dev.anyjava.bot.support;

import dev.anyjava.bot.HomeBotApplication;
import dev.anyjava.bot.work.TorrentScrapingWorker;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
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
    @MockBean
    private TorrentScrapingWorker torrentScrapingWorker;
}
