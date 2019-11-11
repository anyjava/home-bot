package dev.anyjava.bot.torrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = {"home.bot.downloadPath = /var/tmp/TEMP"})
public class FolderWatchServiceTest {
    @Autowired
    FolderWatchService folderWatchService;

    @Ignore // wait 가 걸리는 테스트
    @Test
    public void testWatchService() {
        folderWatchService.watch();
    }
}
