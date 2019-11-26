package dev.anyjava.bot.torrent;

import dev.anyjava.bot.support.TestSupport;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class FolderWatchServiceTest extends TestSupport {
    @Autowired
    FolderWatchService folderWatchService;

    @Disabled("오래걸리는 테스트")
    @Test
    public void testWatchService() {
        folderWatchService.watch();
    }
}
